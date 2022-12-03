// ktlint-disable filename
package day03

import readInput

const val ASCII_LOWER_CASE_SHIFT = 96
const val ASCII_UPPER_CASE_SHIFT = 64
const val LOWER_UPPERCASE_OFFSET = 26
fun main() {
    fun getItemPriority(equalItem: Char) = if (equalItem.isLowerCase()) {
        equalItem.code - ASCII_LOWER_CASE_SHIFT
    } else {
        equalItem.code - ASCII_UPPER_CASE_SHIFT + LOWER_UPPERCASE_OFFSET
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { rucksack ->
            val compartments = rucksack.chunked(rucksack.length / 2)

            val equalItem = compartments[0]
                .firstNotNullOf { item ->
                    item.takeIf {
                        compartments[1].contains(item)
                    }
                }

            getItemPriority(equalItem)
        }
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3)
            .map { group ->
                group[0].firstNotNullOf { item ->
                    item.takeIf {
                        group[1].contains(item) && group[2].contains(item)
                    }
                }
            }
            .sumOf { getItemPriority(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    println("Test results:")
    println(part1(testInput))
    check(part1(testInput) == 157)
    println(part2(testInput))
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println("Final results:")
    println(part1(input))
    println(part2(input))
}
