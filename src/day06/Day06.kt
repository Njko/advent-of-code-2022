// ktlint-disable filename
package day06

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    println("Test results:")
    println(part1(testInput))
    check(part1(testInput) == 0)
    println(part2(testInput))
    check(part2(testInput) == 0)

    val input = readInput("Day06")
    println("Final results:")
    println(part1(input))
    check(part1(input) == 0)
    println(part2(input))
    check(part2(input) == 0)
}
