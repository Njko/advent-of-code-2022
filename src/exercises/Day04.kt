// ktlint-disable filename
package exercises

import readInput

fun main() {
    fun isRangeIncludedInOther(
        elf1: IntRange,
        elf2: IntRange
    ): Boolean {
        if (elf2.first < elf1.first) {
            if (elf2.last >= elf1.last) {
                return true
            }
        } else {
            if (elf2.first == elf1.first) {
                return true
            }
            if (elf2.last <= elf1.last) {
                return true
            }
        }
        return false
    }

    fun isRangeIncludedInOtherV2(
        elf1: IntRange,
        elf2: IntRange
    ): Boolean {
        return elf1.intersect(elf2).containsAll(elf2.toList()) ||
            elf2.intersect(elf1).containsAll(elf1.toList())
    }

    fun part1(input: List<String>): Int {
        var numberOfValidPairs = 0
        input.forEach { line ->
            val bounds = line
                .split(",")
                .map {
                    it.split("-")
                        .let { (lowerBound, upperBound) ->
                            lowerBound.toInt()..upperBound.toInt()
                        }
                }
            val elf1 = bounds[0]
            val elf2 = bounds[1]
            if (isRangeIncludedInOtherV2(elf1, elf2)) {
                numberOfValidPairs++
            }
        }
        return numberOfValidPairs
    }

    fun part2(input: List<String>): Int {
        var numberOfValidPairs = 0
        input.forEach { line ->
            val bounds = line
                .split(",")
                .map {
                    it.split("-")
                        .let { (lowerBound, upperBound) ->
                            lowerBound.toInt()..upperBound.toInt()
                        }
                }
            val elf1 = bounds[0]
            val elf2 = bounds[1]
            if (elf1.intersect(elf2).isNotEmpty()) {
                numberOfValidPairs++
            }
        }
        return numberOfValidPairs
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    println("Test results:")
    println(part1(testInput))
    check(part1(testInput) == 2)
    check(isRangeIncludedInOtherV2(elf1 = 6..6, elf2 = 5..9))
    println(part2(testInput))
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println("Final results:")
    println(part1(input))
    check(part1(input) == 305)
    println(part2(input))
    check(part2(input) == 811)
}
