package com.game.audio

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.utils.GdxRuntimeException

object MusicManager {

    private val music: MutableMap<String, Music> = mutableMapOf()
    private var currentMusic: Music? = null
    var enabled: Boolean = true

    init {
        if(!enabled) {
            println("Music disabled")
        }
    }

    fun load(name: String) {
        val filename = "music/$name.mp3"
        try {
            val musicFile = Gdx.audio.newMusic(Gdx.files.internal(filename))
            music[name] = musicFile
        } catch(exception: GdxRuntimeException) {
            println("Error loading $filename")
        }
    }

    fun unload(name: String) {
        if(name in music) {
            music[name]!!.dispose()
            music.remove(name)
        }
    }

    fun play(name: String, volume: Float) {
        if(currentMusic != null) {
            stop()
        }

        if(name in music) {
            currentMusic = music[name]
            currentMusic!!.volume = volume
            currentMusic!!.isLooping = true
            currentMusic!!.play()
        }
    }

    fun resume() {
        currentMusic?.play()
    }

    fun pause() {
        currentMusic?.pause()
    }

    fun stop() {
        currentMusic?.stop()
        currentMusic = null
    }

}