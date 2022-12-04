package dev.jaredjjohnson.advent.core

class PuzzleLoader {
    fun readLines(filename: String): List<String> = object {}::class.java
            ?.getResourceAsStream("/puzzle_input/$filename")
            ?.bufferedReader()
            ?.readLines()
            ?: emptyList()
}