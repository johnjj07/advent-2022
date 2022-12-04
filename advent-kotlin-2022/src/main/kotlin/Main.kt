import dev.jaredjjohnson.advent.puzzles.day1.Day1Solution
import dev.jaredjjohnson.advent.puzzles.day2.Day2Solution
import dev.jaredjjohnson.advent.puzzles.day3.Day3Solution

fun main() {
    val solutions = listOf(
        Day1Solution(),
        Day2Solution(),
        Day3Solution(),
    )
    solutions.forEach {
        println(it.solutionName())
        println("Part 1: ${it.partOne()}")
        println("Part 2: ${it.partTwo()}")
    }
}