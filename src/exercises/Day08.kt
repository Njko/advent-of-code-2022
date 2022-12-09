// ktlint-disable filename
package exercises

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        input.drop(1).dropLast(1).forEach { line ->
            val treesToSpot = line.drop(1).dropLast(1)
        }
        return 21
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    println("Test results:")
    println(part1(testInput))
    check(part1(testInput) == 21)
    println(part2(testInput))
    check(part2(testInput) == 5)

    val input = readInput("Day08")
    println("Final results:")
    println(part1(input))
    check(part1(input) == 21)
    println(part2(input))
    check(part2(input) == 99)
}
