package dev.jaredjjohnson.advent.puzzles.day2

import dev.jaredjjohnson.advent.core.PuzzleLoader
import dev.jaredjjohnson.advent.core.Solution
import dev.jaredjjohnson.advent.core.Unsolved

class Day2Solution : Solution {
    override val day: Int = 2
    private val input by lazy { PuzzleLoader().readLines(puzzleInput()) }

    override fun partOne() =
        input
            .map(::round)
            .sum()

    override fun partTwo() =
        input
            .map(::round2)
            .sum()
}