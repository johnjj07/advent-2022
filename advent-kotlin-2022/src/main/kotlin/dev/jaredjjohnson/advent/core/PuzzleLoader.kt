package dev.jaredjjohnson.advent.core

import java.io.BufferedReader
import java.io.InputStream

class PuzzleLoader {
    fun readLines(filename: String): List<String> = object {}::class.java
            .getResourceAsStream("/puzzle_input/$filename")
            ?.bufferedReader()
            ?.readLines()
            ?: emptyList()

    fun inputStream(filename: String): InputStream =
        this.javaClass.getResourceAsStream("/puzzle_input/$filename")!!
}