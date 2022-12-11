package dev.jaredjjohnson.advent.puzzles.day5

import java.io.InputStream

fun parseRearrangementProcedure(inputStream: InputStream): Pair<List<StackRow>, List<Instruction>> {
    return inputStream
        .bufferedReader()
        .useLines { line ->
            line.fold(mutableListOf<StackRow>() to mutableListOf<Instruction>()) { (stacks, instructions), s ->
                when {
                    s.contains("[") -> stacks.also { it.add(StackRow(parseCrates(s))) }
                    s.contains("move") -> Instruction.parse(s)?.let { instructions.add(it) }
                }
                stacks to instructions
            }
        }
}