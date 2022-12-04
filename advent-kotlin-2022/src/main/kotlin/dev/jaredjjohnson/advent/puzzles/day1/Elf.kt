package dev.jaredjjohnson.advent.puzzles.day1

data class Elf(val caloriesCarried: Int) {
    fun update(calories: Int): Elf {
        return copy(caloriesCarried = caloriesCarried + calories)
    }
}

fun parseCaloriesData(input: List<String>): List<Elf> {
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
