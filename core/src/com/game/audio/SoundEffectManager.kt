package com.game.audio

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.GdxRuntimeException

object SoundEffectManager {

    private val sounds: MutableMap<String, Sound> = mutableMapOf()
    var enabled: Boolean = true

    init {
        if(!enabled) {
            println("Sound effects disabled")
        }
    }

    fun load(name: String) {
        val filename = "sfx/$name.wave"
        try {
            val sound = Gdx.audio.newSound(Gdx.files.internal(filename))
            sounds[name] = sound
        } catch(exception: GdxRuntimeException) {
            println("Error loading $filename")
        }
    }

    fun unload(name: String) {
        if(name in sounds) {
            sounds[name]!!.dispose()
            sounds.remove(name)
        }
    }

    fun play(name: String, volume: Float = 1f, pitch: Float = 1f, pan: Float = 0f) {
        if(enabled) {
            if(name in sounds) {
                sounds[name]?.play(volume, pitch, pan)
            }
        }
    }

    fun dispose() {
        sounds.asSequence().forEach { it.value.dispose() }
        sounds.clear()
    }
}