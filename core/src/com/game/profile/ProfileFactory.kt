package com.game.profile

import com.game.io.DataFileReader
import java.util.Random

object ProfileFactory {

    val rng = Random()
    val names: Array<String> = DataFileReader.readInternalFile("data/firstnames.txt")

}