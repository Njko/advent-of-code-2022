// ktlint-disable filename
package day01
import readInput
import kotlin.math.max

fun main() {
    fun part1(input: List<String>, excluding: List<Int> = emptyList()): Int {
        var weight = 0
        var maxWeight = 0
        input.forEach { line ->
            if (line.isNotEmpty()) { // last empty line in original input was ignored
                weight += line.toInt()
            } else {
                if (!excluding.contains(weight) && max(weight, maxWeight) == weight) {
                    maxWeight = weight
                }
                weight = 0
            }
        }
        return maxWeight
    }

    fun part2(input: List<String>): Int {
        val firstMaxWeight = part1(input)
        val secondMaxWeight = part1(input, listOf(firstMaxWeight))
        val thirdMaxWeight = part1(input, listOf(firstMaxWeight, secondMaxWeight))

        return firstMaxWeight + secondMaxWeight + thirdMaxWeight
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    println("Test results:")
    println(part1(testInput))
    check(part1(testInput) == 24000)
    println(part2(testInput))
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println("Final results:")
    println(part1(input))
    println(part2(input))
}
