package dev.jaredjjohnson.advent.puzzles.day4

import dev.jaredjjohnson.advent.core.PuzzleLoader
import dev.jaredjjohnson.advent.core.Solution

class Day4Solution : Solution {
    override val day: Int = 4
    private val input by lazy { PuzzleLoader().readLines(puzzleInput()) }

    override fun partOne() = containedPairs(input, ::subsetMatch)
    override fun partTwo() = containedPairs(input, ::partialMatch)
}

private fun containedPairs(input: List<String>, matcher: (Pair<IntRange, IntRange>) -> Boolean): Int {
    return input
        .map(::parse)
        .fold(0) { acc, pair -> if (matcher(pair)) acc + 1 else acc }
}

private fun subsetMatch(pair: Pair<IntRange, IntRange>): Boolean {
    return pair.first.first in pair.second && pair.first.last in pair.second
            || pair.second.first in pair.first && pair.second.last in pair.first
}

private fun partialMatch(pair: Pair<IntRange, IntRange>): Boolean {
    return pair.first.first in pair.second || pair.first.last in pair.second
            || pair.second.first in pair.first || pair.second.last in pair.first
}

private fun parse(line: String): Pair<IntRange, IntRange> {
    val (first, second) = line.split(",")
    val (firstStart, firstEnd) = first.split("-").map { it.toInt() }
    val (secondStart, secondEnd) = second.split("-").map { it.toInt() }
    return firstStart..firstEnd to secondStart..secondEnd
}