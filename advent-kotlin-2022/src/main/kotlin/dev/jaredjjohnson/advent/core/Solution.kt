package dev.jaredjjohnson.advent.core

interface Solution {
    val day: Int
    fun partOne(): Any
    fun partTwo(): Any
    fun puzzleInput() = "day$day.txt"
    fun solutionName() = "Day $day"
}

object Unsolved
