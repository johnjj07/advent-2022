package dev.jaredjjohnson.advent.puzzles.day5

import dev.jaredjjohnson.advent.core.PuzzleLoader
import dev.jaredjjohnson.advent.core.Solution
import java.io.InputStream

class Day5Solution : Solution {
    override val day: Int = 5

    override fun partOne(): Any {
        return loadData(inputStream())
            .let { (stacks, instructions) ->
                val dock = LoadingDock(stacks)
                instructions.forEach { instruction -> dock.move(instruction) }
                dock
                    .tops()
                    .joinToString("") { it.label }
            }
    }

    override fun partTwo(): Any {
        return loadData(inputStream())
            .let { (stacks, instructions) ->
                val dock = LoadingDock(stacks)
                instructions.forEach { instruction -> dock.move2(instruction) }
                dock.tops()
                    .joinToString("") { it.label }
            }
    }

    private fun loadData(inputStream: InputStream): Pair<List<MutableList<Crate>>, List<Instruction>> {
        val (stackRows, instructions) = parseRearrangementProcedure(inputStream)
        val stackCount = stackRows.flatMap { it.crates }.maxOf { it.location }
        val stacks = List(stackCount + 1) { mutableListOf<Crate>() }
        stackRows
            .flatMap { it.crates }
            .forEach { stacks[it.location].add(0, it) }
        return stacks to instructions
    }

    private fun inputStream(): InputStream {
        return PuzzleLoader().inputStream(puzzleInput())
    }
}

fun parseCrates(line: String): List<Crate> {
    return line.chunked(4)
        .mapIndexedNotNull { index, s ->
            s
                .takeIf { it.startsWith("[") && it.length >= 3 }
                ?.getOrNull(1)
                ?.let { Crate(it.toString(), index) }
        }
}

data class Crate(val label: String, val location: Int)

data class StackRow(val crates: List<Crate>)

class LoadingDock(private val stacks: List<MutableList<Crate>>) {
    fun move(instruction: Instruction) {
        repeat(instruction.amount) {
            val item = stacks[instruction.fromIndex - 1].removeLast()
            stacks[instruction.toIndex - 1].add(item)
        }
    }

    fun move2(instruction: Instruction) {
        val t = mutableListOf<Crate>()
        repeat(instruction.amount) { t.add(stacks[instruction.fromIndex - 1].removeLast()) }
        stacks[instruction.toIndex - 1].addAll(t.reversed())
    }

    fun tops(): List<Crate> {
        return stacks.map { it.last() }
    }

    override fun toString(): String {
        return (1..stacks.size).joinToString(" ") { "$it(${stacks[it - 1].size})"  }
    }
}
