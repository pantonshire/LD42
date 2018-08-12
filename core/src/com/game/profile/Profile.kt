package com.game.profile

import com.badlogic.gdx.math.MathUtils
import java.util.*

class Profile(
        val forename: String,
        val lastname: String,
        val age: String,
        val greeting: String,
        val legitimate: Boolean,
        val relation : String,
        val gender : Gender
) {

    fun fullName(): String = "$forename $lastname"

    fun ageAsInt(): Int = Integer.valueOf(age)

    fun pcgFaceParts(): Array<Int> {
        val seed = (forename + lastname).hashCode()
        val rng = Random(seed.toLong())
        return IntArray(6) { _ -> rng.nextInt(5) }.toTypedArray()
    }

    override fun toString(): String {
        return "$forename $lastname, $age, $gender ($legitimate)"
    }

}
