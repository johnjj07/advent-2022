package dev.jaredjjohnson.advent.puzzles.day3

import dev.jaredjjohnson.advent.core.PuzzleLoader
import dev.jaredjjohnson.advent.core.Solution

class Day3Solution : Solution{
    override val day: Int = 3
    private val input by lazy { PuzzleLoader().readLines(puzzleInput()) }

    override fun partOne() = input
        .map(::findMatch)
        .sum()

    override fun partTwo() = input
        .chunked(3)
        .map(::findBadge)
        .sum()
}

fun findMatch(sack: String): Int {
    val (first, second) = sack.chunked(sack.length/2).map { it.toSet() }
    return first
        .intersect(second)
        .first()
        .code
        .let(::prioritize)
}


fun findBadge(group: List<String>): Int {
    val groupedSet = group.map { it.toSet() }
    return groupedSet
        .fold(groupedSet.first()) { acc, chars -> acc.intersect(chars) }
        .first()
        .code
        .let(::prioritize)
}

private fun prioritize(code: Int): Int {
    // ASCII table, to normalize a to 1, and A to 27
    return code.mod(96).let { if (it > 26) it - 38 else it }
}
