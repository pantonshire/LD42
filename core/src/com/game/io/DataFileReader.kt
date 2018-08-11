package com.game.io

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

object DataFileReader {

    fun readInternalFile(path: String): Array<String> {
        val lines: MutableList<String> = mutableListOf()
        try {
            val inputStream = javaClass.getResourceAsStream(path)
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.readLines().asSequence().forEach { lines.add(it) }
            reader.close()
        } catch(exception: Exception) {
            println("Error reading internal file $path")
        }

        return lines.toTypedArray()
    }

}