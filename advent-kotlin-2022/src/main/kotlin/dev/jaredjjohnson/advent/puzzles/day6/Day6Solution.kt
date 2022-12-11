package dev.jaredjjohnson.advent.puzzles.day6

import dev.jaredjjohnson.advent.core.PuzzleLoader
import dev.jaredjjohnson.advent.core.Solution

class Day6Solution : Solution {
    override val day: Int = 6
    private val input by lazy { PuzzleLoader().readLines(puzzleInput()) }

    override fun partOne() =
        input.sumOf { slidingWindow(it, 4, 0) }

    override fun partTwo() =
        input.sumOf { slidingWindow(it, 14, 0) }
}

private tailrec fun slidingWindow(line: String, uniqueness: Int, index: Int): Int {
    val size = line
        .take(uniqueness)
        .toSet()
        .size
    return if (size == uniqueness) {
        index + uniqueness
    } else {
        slidingWindow(line.drop(1), uniqueness, index + 1)
    }
}