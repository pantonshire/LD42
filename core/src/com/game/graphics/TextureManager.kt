package com.game.graphics

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.GdxRuntimeException

object TextureManager: Disposable {

    /* Map of loaded textures by name. */
    private val textures: MutableMap<String, Texture> = mutableMapOf()
    /* Texture to be used if loading another fails. */
    private val unknown: Texture = Texture("textures/unknown.png")

    /* Load the specified texture and add it to the map. */
    private fun load(name: String) {
        val filename = "textures/$name.png"
        try {
            val texture = Texture(filename)
            textures[name] = texture
        } catch(exception: GdxRuntimeException) {
            println("Error loading $filename")
        }
    }

    /* Looks up the texture in the map and returns it if it exists. Otherwise, return the
     * unknown texture. */
    fun get(name: String): Texture {
        if(!textures.containsKey(name)) {
            load(name)
        }
        return textures[name] ?: unknown
    }

    /* Dispose all textures and clear the map. */
    override fun dispose() {
        textures.asSequence().forEach { it.value.dispose() }
        textures.clear()
    }
}