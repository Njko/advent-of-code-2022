// ktlint-disable filename
package exercises

import readInput
import kotlin.math.pow
import kotlin.math.sqrt

data class Vec2(val x: Int, val y: Int)

operator fun Vec2.plus(minusVec2: Vec2): Vec2 = Vec2(this.x + minusVec2.x, this.y + minusVec2.y)
operator fun Vec2.minus(minusVec2: Vec2): Vec2 = Vec2(this.x - minusVec2.x, this.y - minusVec2.y)

fun Vec2.isNotCloseTo(position: Vec2): Boolean {
    return sqrt(
        (position.x - this.x).toDouble().pow(2) + (position.y - this.y).toDouble().pow(2)
    ) > 1.5
}

fun main() {
    fun moveTailToHead(tailPositon: Vec2, headPosition: Vec2): Vec2 {
        val direction = headPosition - tailPositon
        val normalizedDirection = Vec2(
            direction.x.coerceIn(-1..1),
            direction.y.coerceIn(-1..1)
        )
        return tailPositon + normalizedDirection
    }

    fun part1(input: List<String>): Int {
        var headPosition = Vec2(0, 0)
        var tailPositon = Vec2(0, 0)
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
        return positionVisited.size
    }

    fun part2(input: List<String>): Int {
        val positionVisited = mutableSetOf(Vec2(0, 0))
        val longTail = generateSequence { Vec2(0, 0) }.take(10).toMutableList()
        input.forEach { instruction ->
            val (direction, steps) = instruction.split(" ")
            repeat(steps.toInt()) {
                when (direction) {
                    "U" -> longTail[0] = longTail[0].copy(y = longTail[0].y + 1)
                    "D" -> longTail[0] = longTail[0].copy(y = longTail[0].y - 1)
                    "L" -> longTail[0] = longTail[0].copy(x = longTail[0].x - 1)
                    "R" -> longTail[0] = longTail[0].copy(x = longTail[0].x + 1)
                }

                longTail.dropLast(1).forEachIndexed { tailElementIndex, _ ->
                    val currentHead = longTail[tailElementIndex]
                    val currentTail = longTail[tailElementIndex + 1]

                    if (currentHead.isNotCloseTo(currentTail)) {
                        longTail[tailElementIndex + 1] = moveTailToHead(
                            headPosition = currentHead,
                            tailPositon = currentTail
                        )
                    }
                }

                positionVisited.add(longTail.last())
            }
        }
        return positionVisited.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    val testInput2 = readInput("Day09_test2")
    println("Test results:")
    println(part1(testInput))
    check(part1(testInput) == 13)

    println(part2(testInput))
    check(part2(testInput) == 1)

    println(part2(testInput2))
    check(part2(testInput2) == 36)

    val input = readInput("Day09")
    println("Final results:")
    println(part1(input))
    check(part1(input) == 6087)
     println(part2(input))
    check(part2(input) == 2493)
}
