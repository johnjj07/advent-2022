package dev.jaredjjohnson.advent.puzzles.day1

data class Elf(val caloriesCarried: Int) {
    fun update(calories: Int): Elf {
        return copy(caloriesCarried = caloriesCarried + calories)
    }
}
