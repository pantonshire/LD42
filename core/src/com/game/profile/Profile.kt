package com.game.profile

import com.badlogic.gdx.math.MathUtils
import com.game.graphics.Layer
import com.game.graphics.TextureManager
import java.util.*

class Profile(
        val forename: String,
        val lastname: String,
        val age: String,
        val greeting: String,
        val legitimate: Boolean,
        val relation : String,
        val gender : Gender,
        val profilePictureGender: Gender
) {

    val faceParts = pcgFaceParts()

    fun fullName(): String = "$forename $lastname"

    fun ageAsInt(): Int = Integer.valueOf(age)

    fun pcgFaceParts(): Array<Int> {
        val seed = (forename + lastname).hashCode()
        val rng = Random(seed.toLong())
        return IntArray(6) { _ -> rng.nextInt(3) }.toTypedArray()
    }

    override fun toString(): String {
        return "$forename $lastname, $age, $gender ($legitimate)"
    }

    fun drawFace(layer: Layer, x: Float, y: Float) {
        val head = TextureManager.get("head${faceParts[0]}")
        val eyes = TextureManager.get("eyes${faceParts[1]}")
        val mouth = TextureManager.get("mouth${faceParts[2]}")
        val hair = TextureManager.get("hair${profilePictureGender.letter()}${faceParts[3]}")

        layer.fastDraw(head, x, y)
        layer.fastDraw(eyes, x+17, y+30)
        layer.fastDraw(mouth, x+17, y+16)
        layer.fastDraw(hair, x+5, y+2)
    }

}
