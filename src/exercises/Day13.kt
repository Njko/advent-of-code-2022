// ktlint-disable filename
package exercises

import readInput

// https://www.youtube.com/watch?v=MF702EsPnAc&t=166s
fun main() {
    fun part1(input: List<String>): Int {
        val pairsToCompare = input.windowed(2,3).toList()
        pairsToCompare.forEach { pair ->
            var indexLeft = 0
            var indexRight = 0
            val leftItem = pair[0]
            val rightItem = pair[1]
        }

        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day13_test")
    println("Test results:")
    println(part1(testInput))
    /*check(part1(testInput) == 23)
    println(part2(testInput))
    check(part2(testInput) == 0)

    val input = readInput("Day13")
    println("Final results:")
    println(part1(input))
    check(part1(input) == 0)
    println(part2(input))
    check(part2(input) == 0)*/
}
