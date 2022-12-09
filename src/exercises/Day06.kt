// ktlint-disable filename
package exercises

import readInput

fun main() {
    // Can be solved with: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/windowed-sequence.html
    fun searchIndexOfMarker(data: String, markerSize: Int): Int {
        var startIndex = 0
        var indexOfMarker = markerSize
        while (data.substring(startIndex until startIndex + markerSize)
            .toList()
            .distinct()
            .size < markerSize
        ) {
            // continue looking for marker
            startIndex += 1
            indexOfMarker += 1
        }
        return indexOfMarker
    }

    fun part1(input: String): Int {
        return searchIndexOfMarker(input, 4)
    }

    fun part2(input: String): Int {
        return searchIndexOfMarker(input, 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    println("Test results:")
    println(part1(testInput[0]))
    check(part1(testInput[0]) == 5)
    check(part1("nppdvjthqldpwncqszvftbrmjlhg") == 6)
    check(part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") == 10)
    check(part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") == 11)
    println(part2("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
    check(part2("mjqjpqmgbljsphdztnvjfqwrcgsmlb") == 19)
    check(part2("bvwbjplbgvbhsrlpgdmjqwftvncz") == 23)
    check(part2("nppdvjthqldpwncqszvftbrmjlhg") == 23)
    check(part2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") == 29)
    check(part2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") == 26)

    val input = readInput("Day06")
    // println("Final results:")
    println(part1(input[0]))
    check(part1(input[0]) == 1707)
    println(part2(input[0]))
    check(part2(input[0]) == 3697)
}
