package com.game.profile

import com.game.io.DataFileReader
import com.game.maths.RandomUtils
import java.util.*

object ProfileFactory {

    val rng = Random()
    val maleNames: Array<String> = DataFileReader.readInternalFile("data/male_names.txt")
    val femaleNames: Array<String> = DataFileReader.readInternalFile("data/female_names.txt")
    val surnames: Array<String> = DataFileReader.readInternalFile("data/male_names.txt")

    fun forename(gender: Gender) : String {
        return RandomUtils.choose(if (gender == Gender.MALE) maleNames else femaleNames);
    }

    fun surname() : String {
        return RandomUtils.choose(surnames);
    }

    fun buildRandomProfile() : Profile{
        val gender : Gender = RandomUtils.choose(arrayOf(Gender.MALE, Gender.FEMALE))
        val generated = Profile(forename(gender), surname(), RandomUtils.randRange(0..100).toString(), "some_greeting", true, "myself", gender );
        return generated
    }

    fun genMe() : Profile {
        val me : Profile = buildRandomProfile()
        return me;
    }

}