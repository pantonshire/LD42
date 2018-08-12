package com.game.profile

import com.badlogic.gdx.graphics.Texture
import com.game.io.DataFileReader
import com.game.maths.RandomUtils
import java.util.*

object ProfileFactory {

    val rng = Random()
    val maleNames = DataFileReader.readInternalFile("data/male_names.txt")
    val femaleNames = DataFileReader.readInternalFile("data/female_names.txt")
    val dodgyNames = DataFileReader.readInternalFile("data/dodgy_names.txt")
    val surnames = DataFileReader.readInternalFile("data/surnames.txt")
    val genders = arrayOf(Gender.MALE, Gender.FEMALE)

    val possibleInconsistencies = 6


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

    fun newLegitimate(): Profile {
        val gender = RandomUtils.choose(genders)
        val forename = when(gender) {
            Gender.MALE -> RandomUtils.choose(maleNames)
            Gender.FEMALE -> RandomUtils.choose(femaleNames)
        }
        val surname = RandomUtils.choose(surnames)
        val age = RandomUtils.randRange(20..50)
        val greeting = "lorem ipsum"
        val relation = "lorem ipsum"

        return Profile(forename, surname, age.toString(), greeting, true, relation, gender, gender)
    }

    fun newScammer(numInconsistencies: Int): Profile {
        val inconsistencies =
                (BooleanArray(numInconsistencies) { _ -> true } +
                BooleanArray(possibleInconsistencies - numInconsistencies) { _ -> false })
                .toList()
        Collections.shuffle(inconsistencies)

        val gender = RandomUtils.choose(genders)

        val forename = when(if(inconsistencies[0]) gender.other() else gender) {
            Gender.MALE -> RandomUtils.choose(maleNames)
            Gender.FEMALE -> RandomUtils.choose(femaleNames)
        }

        val surname = if(inconsistencies[1]) {
            RandomUtils.choose(dodgyNames)
        } else {
            RandomUtils.choose(surnames)
        }

        val age = if(inconsistencies[2]) {
            if(RandomUtils.flipCoin()) RandomUtils.randRange(1..10) else RandomUtils.randRange(100..200)
        } else {
            RandomUtils.randRange(20..50)
        }

        val greeting = if(inconsistencies[3]) {
            "foo baa"
        } else {
            "lorem ipsum"
        }

        val relation = if(inconsistencies[4]) {
            "foo baa"
        } else {
            "lorem ipsum"
        }

        val profilePictureGender = if(inconsistencies[5]) gender.other() else gender

        return Profile(forename, surname, age.toString(), greeting, true, relation, gender, profilePictureGender)
    }


}