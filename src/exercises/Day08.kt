// ktlint-disable filename
package exercises

import readInput
import kotlin.math.max

// https://www.youtube.com/watch?v=6d6FXFh-UdA
fun main() {
    fun part1(input: List<String>): Int {
        val forestSize = input[0].length
        var centerTreesVisible = 0
        val grid = mutableListOf<List<Int>>()

        input.forEach { line ->
            val valueLine = mutableListOf<Int>()
            line.forEach {
                valueLine.add(it.toString().toInt())
            }
            grid.add(valueLine)
        }

        grid.drop(1).dropLast(1).forEachIndexed { y, line ->
            val treesToSpot = line.drop(1).dropLast(1)
            treesToSpot.toList().forEachIndexed { x, treeHeight ->
                val treeX = x + 1
                val treeY = y + 1

                val treesAbove = mutableListOf<Int>()
                val treesBelow = mutableListOf<Int>()
                val treesOnRight = mutableListOf<Int>()
                val treesOnLeft = mutableListOf<Int>()

                for (index in treeY - 1 downTo 0) {
                    treesAbove.add(grid[index][treeX])
                }
                for (index in treeY + 1 until forestSize) {
                    treesBelow.add(grid[index][treeX])
                }
                for (index in treeX - 1 downTo 0) {
                    treesOnLeft.add(grid[treeY][index])
                }
                for (index in treeX + 1 until forestSize) {
                    treesOnRight.add(grid[treeY][index])
                }

                val visibleFromTop = treesAbove.reversed().indexOfLast { treesAbove.max() >= treeHeight } == -1
                val visibleFromBottom = treesBelow.reversed().indexOfLast { treesBelow.max() >= treeHeight } == -1
                val visibleFromLeft = treesOnLeft.reversed().indexOfLast { treesOnLeft.max() >= treeHeight } == -1
                val visibleFromRight = treesOnRight.reversed().indexOfLast { treesOnRight.max() >= treeHeight } == -1

                val isVisible =
                    visibleFromTop || visibleFromLeft || visibleFromBottom || visibleFromRight

                if (isVisible) centerTreesVisible += 1
            }
        }
        val edges = forestSize * 2 + (forestSize - 2) * 2
        return edges + centerTreesVisible
    }

    fun findViewDistance(treeHeight: Int, trees: List<Int>): Int {
        if (trees.size == 1) {
            return 1
        }
        var viewDistance = 0

        for (tree in trees) {
            viewDistance += 1
            if (tree >= treeHeight) break
        }
        return viewDistance
    }

    fun part2(input: List<String>): Int {
        val forestSize = input[0].length
        val grid = mutableListOf<List<Int>>()
        var maxScenicScore = 0

        input.forEach { line ->
            val valueLine = mutableListOf<Int>()
            line.forEach {
                valueLine.add(it.toString().toInt())
            }
            grid.add(valueLine)
        }

        grid.drop(1).dropLast(1).forEachIndexed { y, line ->
            val treesToSpot = line.drop(1).dropLast(1)
            treesToSpot.toList().forEachIndexed { x, treeHeight ->
                val treeX = x + 1
                val treeY = y + 1

                val treesAbove = mutableListOf<Int>()
                val treesBelow = mutableListOf<Int>()
                val treesOnRight = mutableListOf<Int>()
                val treesOnLeft = mutableListOf<Int>()

                for (index in treeY - 1 downTo 0) {
                    treesAbove.add(grid[index][treeX])
                }
                for (index in treeY + 1 until forestSize) {
                    treesBelow.add(grid[index][treeX])
                }
                for (index in treeX - 1 downTo 0) {
                    treesOnLeft.add(grid[treeY][index])
                }
                for (index in treeX + 1 until forestSize) {
                    treesOnRight.add(grid[treeY][index])
                }

                val distanceAbove = findViewDistance(treeHeight, treesAbove)
                val distanceBelow = findViewDistance(treeHeight, treesBelow)
                val distanceOnLeft = findViewDistance(treeHeight, treesOnLeft)
                val distanceOnRight = findViewDistance(treeHeight, treesOnRight)

                val scenicScore = distanceAbove * distanceBelow * distanceOnLeft * distanceOnRight
                maxScenicScore = max(maxScenicScore, scenicScore)
            }
        }
        return maxScenicScore
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    println("Test results:")
    println(part1(testInput))
    check(part1(testInput) == 21)
    println(part2(testInput))
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println("Final results:")
    println(part1(input))
    check(part1(input) == 1713)
    println(part2(input))
    check(part2(input) == 268464)
}
