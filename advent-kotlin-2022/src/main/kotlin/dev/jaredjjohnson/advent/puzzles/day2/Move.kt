package dev.jaredjjohnson.advent.puzzles.day2

// TODO: Maybe make an enum
sealed interface Move : Comparable<Move> {
    val value: Int
    fun winsAgainst(): Move
    fun losesTo(): Move

    companion object {
        fun parse(value: String): Move {
            return when (value) {
                "A", "X" -> Rock
                "B", "Y" -> Paper
                "C", "Z" -> Scissor
                else -> throw Exception("Unsolvable")
            }
        }
    }
}
object Rock : Move {
    override val value: Int = 1
    override fun winsAgainst() = Scissor
    override fun losesTo() = Paper

    override fun compareTo(other: Move): Int {
        return when (other) {
            Paper -> -1
            Rock -> 0
            Scissor -> 1
        }
    }
}
object Paper : Move {
    override val value: Int = 2
    override fun winsAgainst() = Rock
    override fun losesTo() = Scissor
    override fun compareTo(other: Move): Int {
        return when (other) {
            Paper -> 0
            Rock -> 1
            Scissor -> -1
        }
    }
}
object Scissor : Move {
    override val value: Int = 3
    override fun winsAgainst() = Paper
    override fun losesTo() = Rock
    override fun compareTo(other: Move): Int {
        return when (other) {
            Paper -> 1
            Rock -> -1
            Scissor -> 0
        }
    }
}

sealed interface Result {
    val points: Int

    fun achieveFrom(opponent: Move): Move

    companion object {
        fun parse(desiredResult: String): Result {
            return when(desiredResult) {
                "X" -> Lose
                "Y" -> Draw
                "Z" -> Win
                else -> throw Exception("Unsolvable")
            }
        }
    }
}

object Win : Result  {
    override val points: Int = 6
    override fun achieveFrom(opponent: Move): Move {
        return opponent.losesTo()
    }
}

object Lose : Result {
    override val points: Int = 0
    override fun achieveFrom(opponent: Move): Move {
        return opponent.winsAgainst()
    }
}

object Draw : Result {
    override val points: Int = 1
    override fun achieveFrom(opponent: Move): Move {
        return opponent
    }
}

fun round(roundGuide: String): Int {
    val (opponent, me) = roundGuide.split(" ").map { Move.parse(it) }
    return round(opponent, me)
}

fun round(opponent: Move, me: Move): Int {
    return when {
        opponent > me -> me.value
        opponent < me -> me.value + 6
        opponent == me -> me.value + 3
        else -> 0
    }
}

fun round2(roundGuide: String): Int {
    val (opponent, result) = roundGuide.split(" ")
    val opponentMove = Move.parse(opponent)
    return round(opponentMove, Result.parse(result).achieveFrom(opponentMove))
}
