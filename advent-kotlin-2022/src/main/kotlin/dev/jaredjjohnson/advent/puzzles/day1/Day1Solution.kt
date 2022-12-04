package dev.jaredjjohnson.advent.puzzles.day1

import dev.jaredjjohnson.advent.core.PuzzleLoader
import dev.jaredjjohnson.advent.core.Solution

class Day1Solution : Solution {
    override val day: Int = 1
    private val input by lazy { PuzzleLoader().readLines(puzzleInput()) }

    override fun partOne() =
        parseCaloriesData(input)
            .maxBy { it.caloriesCarried }

    override fun partTwo() =
        parseCaloriesData(input)
            .sortedBy { it.caloriesCarried }
            .takeLast(3)
            .sumOf { it.caloriesCarried }

    private fun parseCaloriesData(input: List<String>): List<Elf> {
        return input
            .fold(mutableListOf<Elf>() to Elf(0)) { acc, s ->
                val (elves, currentElf) = acc
                when {
                    elves.isEmpty() -> {
                        val elf = s.toIntOrNull()?.let { currentElf.update(s.toInt()) } ?: currentElf
                        elves.also { it.add(elf) } to elf
                    }
                    s.isEmpty() -> {
                        elves.also{ it.add(currentElf) } to Elf(0)
                    }
                    else -> {
                        elves to currentElf.update(s.toInt())
                    }
                }
            }
            .first
    }
}