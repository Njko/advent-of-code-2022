// ktlint-disable filename
package exercises

import readInput
import kotlin.math.abs

data class Scan(
    val sensorAtX: Int,
    val sensorAtY: Int,
    val closestBeaconAtX: Int,
    val closestBeaconAtY: Int,
    val distance: Int
)

fun main() {
    fun extractScans(data: List<String>): List<Scan> {
        val pattern = "^.* x=(-?\\d*), y=(-?\\d*).* x=(-?\\d*), y=(-?\\d*)\$".toRegex()
        val scans = mutableListOf<Scan>()
        for (line in data) {
            pattern.findAll(line).forEach { matchResult ->
                val sensorAtX = matchResult.groupValues[1].toInt()
                val sensorAtY = matchResult.groupValues[2].toInt()
                val closestBeaconAtX = matchResult.groupValues[3].toInt()
                val closestBeaconAtY = matchResult.groupValues[4].toInt()
                val distanceX = abs(closestBeaconAtX - sensorAtX)
                val distanceY = abs(closestBeaconAtY - sensorAtY)
                scans.add(
                    Scan(
                        sensorAtX = sensorAtX,
                        sensorAtY = sensorAtY,
                        closestBeaconAtX = closestBeaconAtX,
                        closestBeaconAtY = closestBeaconAtY,
                        distance = distanceX + distanceY
                    )
                )
            }
        }
        return scans
    }

    fun part1(input: List<String>, lineScanned: Int): Int {
        val scans = extractScans(input)

        val beaconsRangeIntersectsLine = scans.filter {
            (it.sensorAtY - it.distance..it.sensorAtY + it.distance).contains(lineScanned)
        }

        val intersection = mutableSetOf<Int>()
        beaconsRangeIntersectsLine.forEach { scan ->
            val xDistance = scan.distance - abs(lineScanned - scan.sensorAtY)
            val xRange = scan.sensorAtX - xDistance..scan.sensorAtX + xDistance
            xRange.forEach {
                intersection.add(it)
            }
        }

        return intersection.size - 1
    }

    fun findLineWithHole(scans: List<Scan>, gridSize: Int): Int {
        for (lineIndex in 0..gridSize) {
            val beaconsRangeIntersectsLine = scans.filter {
                (it.sensorAtY - it.distance..it.sensorAtY + it.distance).contains(lineIndex)
            }

            val intersection = mutableSetOf<Int>()
            beaconsRangeIntersectsLine.forEach { scan ->
                val xDistance = (scan.distance - abs(lineIndex - scan.sensorAtY))
                val xMin = (scan.sensorAtX - xDistance).coerceIn(0..gridSize)
                val xMax = (scan.sensorAtX + xDistance).coerceIn(0..gridSize)
                val xRange = xMin..xMax
                xRange.forEach {
                    intersection.add(it)
                }
            }

            if (intersection.size - 1 != gridSize) {
                return lineIndex
            }
        }
        return -1
    }

    fun findPositionOfHole(scans: List<Scan>, gridSize: Int, y: Int): Int {
        return 14
    }

    fun part2(input: List<String>, gridSize: Int): Int {
        val scans = extractScans(input)
        val y = findLineWithHole(scans, gridSize) // too slow
        val x = findPositionOfHole(scans, gridSize, y)
        return y
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day15_test")
    println("Test results:")
    // println(part1(testInput, 10))
    // check(part1(testInput) == 26)
    // println(part2(testInput, 20))
    // check(part2(testInput) == 56000011)

    val input = readInput("Day15")
    val startTime = System.currentTimeMillis()
    println("Final results:")
    println(part1(input, 2000000))
    val endOfPart1 = System.currentTimeMillis()
    println("ended part 1 in ${endOfPart1 - startTime} ms")
    // check(part1(input) == 5525990)
    println(part2(input, 4000000))
    // check(part2(input) == 0)
    println("End")
}
