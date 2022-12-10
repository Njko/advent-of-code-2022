// ktlint-disable filename
package exercises

import readInput
import kotlin.math.pow
import kotlin.math.sqrt

data class Position(val x: Int, val y: Int)

fun Position.isNotCloseTo(position: Position): Boolean {
    return sqrt(
        (position.x - this.x).toDouble().pow(2) + (position.y - this.y).toDouble().pow(2)
    ) > 2
}

fun main() {
    fun moveTailToHead(tailPositon: Position, headPosition: Position): Position {
        // same X axis : change Y

        // same Y axis : change X

        // diagonal along X axis : change X and Y to match Y

        // diagonal along Y axis : change X and Y to match X
        return headPosition
    }

    fun part1(input: List<String>): Int {
        var headPosition = Position(0, 0)
        var tailPositon = Position(0, 0)
        val positionVisited = mutableSetOf(tailPositon)
        input.forEach { instruction ->
            val (direction, steps) = instruction.split(" ")
            repeat(steps.toInt()) {
                when (direction) {
                    "U" -> headPosition = headPosition.copy(y = headPosition.y + 1)
                    "D" -> headPosition = headPosition.copy(y = headPosition.y - 1)
                    "L" -> headPosition = headPosition.copy(x = headPosition.x - 1)
                    "R" -> headPosition = headPosition.copy(x = headPosition.x + 1)
                }
                if (tailPositon.isNotCloseTo(headPosition)) {
                    tailPositon = moveTailToHead(tailPositon, headPosition)
                }
                positionVisited.add(tailPositon)
            }
        }
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    println("Test results:")
    println(part1(testInput))
    check(part1(testInput) == 8)
    check(Position(x = 2, y = 0).isNotCloseTo(Position(x = 1, y = 2)))
    /*println(part2(testInput))
    check(part2(testInput) == 0)

    val input = readInput("Day09")
    println("Final results:")
    println(part1(input))
    check(part1(input) == 0)
    println(part2(input))
    check(part2(input) == 0)*/
}
