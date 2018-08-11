package com.game.graphics

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.GdxRuntimeException
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter

object FontManager: Disposable {

    private val fonts: MutableMap<String, BitmapFont> = mutableMapOf()
    private val default: BitmapFont = BitmapFont()

    fun load(name: String, size: Int) {
        val filename = "fonts/$name.ttf"
        try {
            val generator = FreeTypeFontGenerator(Gdx.files.internal(filename))
            val parameter = FreeTypeFontParameter()
            parameter.size = size
            parameter.mono = true
            parameter.kerning = true
            val font = generator.generateFont(parameter)
            val referenceName = "$name-$size"
            fonts[referenceName] = font
        } catch(exception: GdxRuntimeException) {
            println("Error loading $filename")
        }
    }

    fun get(font: String, size: Int): BitmapFont {
        val key = "$font-$size"
        if(!fonts.containsKey(key)) {
            load(font, size)
        }

        return fonts["$font-$size"] ?: default
    }

    override fun dispose() {
        fonts.asSequence().forEach { it.value.dispose() }
        fonts.clear()
    }
}