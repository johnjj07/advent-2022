package dev.jaredjjohnson.advent.puzzles.day5

data class Instruction(val amount: Int, val fromIndex: Int, val toIndex: Int) {
    companion object {
        private val regex = """move\s(\d+)\sfrom\s(\d+)\sto\s(\d+)""".toRegex()
        fun parse(line: String): Instruction? {
            return regex
                .matchEntire(line)
                ?.groupValues
                ?.drop(1)
                ?.map { it.toInt() }
                ?.takeIf { it.size == 3 }
                ?.let {
                    val (amount, from, to) = it
                    Instruction(amount, from, to)
                }
        }
    }
}
