// ktlint-disable filename
package day02

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var playerWin = 0
        input.forEach { line ->
            when (line) {
                "B X" -> playerWin += 1 // loss with rock
                "C Y" -> playerWin += 2 // loss with paper
                "A Z" -> playerWin += 3 // loss with scissors
                "C X" -> playerWin += (1 + 6) // win with rock
                "A Y" -> playerWin += (2 + 6) // win with paper
                "B Z" -> playerWin += (3 + 6) // win with scissors
                "A X" -> playerWin += (1 + 3) // draw with rock
                "B Y" -> playerWin += (2 + 3) // draw with paper
                "C Z" -> playerWin += (3 + 3) // draw with scissors
                else -> { /*nothing */
                }
            }
        }

        return playerWin
    }

    fun part2(input: List<String>): Int {
        var playerWin = 0
        input.forEach { line ->
            when (line) {
                "B X" -> playerWin += 1 // paper + loose = rock
                "C Y" -> playerWin += (3 + 3) // scissors + draw = scissors
                "A Z" -> playerWin += (2 + 6) // rock + win = paper
                "C X" -> playerWin += 2 // scissors + loose = paper
                "A Y" -> playerWin += (1 + 3) // rock + draw = rock
                "B Z" -> playerWin += (3 + 6) // paper + win = scissors
                "A X" -> playerWin += 3 // rock + loose = scissors
                "B Y" -> playerWin += (2 + 3) // paper + draw = paper
                "C Z" -> playerWin += (1 + 6) // scissors + win = rock
                else -> { /*nothing */
                }
            }
        }

        return playerWin
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    println("Test results:")
    println(part1(testInput))
    check(part1(testInput) == 15)
    println(part2(testInput))
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println("Final results:")
    println(part1(input))
    println(part2(input))
}
