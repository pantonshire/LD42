package com.game.profile

import com.game.io.DataFileReader
import com.game.maths.RandomUtils
import java.util.Random

object ProfileFactory {

    val rng = Random()
    val names: Array<String> = DataFileReader.readInternalFile("data/names_new.txt")


    fun genName() : String {
        val firstName : String = RandomUtils.choose(names);
        val secondName : String = RandomUtils.choose(names);
        val deliminator : String = RandomUtils.choose(arrayOf(" ",".","-"));
        val name = "$firstName$deliminator$secondName"
        return name
    }
}