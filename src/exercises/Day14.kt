// ktlint-disable filename
package exercises

import readInput

data class Coord(val x: Int, val y: Int)

fun main() {
    fun extractPaths(input: List<String>) = input.map { line ->
        val points = line.split(" -> ")
        points.map {
            val coord = it.split(",")
            Coord(coord[0].toInt(), coord[1].toInt())
        }.toList()
    }

    fun fillPaths(paths: List<List<Coord>>): List<List<Coord>> {
        val filledPaths = mutableListOf<List<Coord>>()

        paths.forEach { path ->
            val filledPath = mutableSetOf<Coord>()
            path.windowed(2, 1, partialWindows = false).toList().map { edges ->
                val start = edges[0]
                val end = edges[1]
                if (start.x == end.x) {
                    if (start.y < end.y) {
                        for (i in start.y..end.y) {
                            filledPath.add(Coord(start.x, i))
                        }
                    } else {
                        for (i in end.y..start.y) {
                            filledPath.add(Coord(start.x, i))
                        }
                    }
                } else {
                    if (start.x < end.x) {
                        for (i in start.x..end.x) {
                            filledPath.add(Coord(i, start.y))
                        }
                    } else {
                        for (i in end.x..start.x) {
                            filledPath.add(Coord(i, start.y))
                        }
                    }
                }
            }
            filledPaths.add(filledPath.toList())
        }
        return filledPaths.toList()
    }

    fun canGoDownTo(currentGrain: Coord, obstacles: List<Coord>): Coord? {
        val possiblePosition =
            mutableListOf(
                Coord(currentGrain.x, currentGrain.y + 1),
                Coord(currentGrain.x - 1, currentGrain.y + 1),
                Coord(currentGrain.x + 1, currentGrain.y + 1)
            )
        obstacles
            .filter { currentGrain.y + 1 == it.y }
            .filter {
                currentGrain.x - 1 == it.x ||
                    currentGrain.x == it.x ||
                    currentGrain.x + 1 == it.x
            }.map { obstacle ->
                possiblePosition.remove(obstacle)
            }

        return possiblePosition.firstOrNull()
    }

    fun canGoDownToV2(currentGrain: Coord, obstacles: List<Coord>, floorLevel: Int): Coord? {
        val possiblePosition =
            mutableListOf(
                Coord(currentGrain.x, currentGrain.y + 1),
                Coord(currentGrain.x - 1, currentGrain.y + 1),
                Coord(currentGrain.x + 1, currentGrain.y + 1)
            )

        val floorPosition = listOf(
            Coord(currentGrain.x, floorLevel),
            Coord(currentGrain.x - 1, floorLevel),
            Coord(currentGrain.x + 1, floorLevel)
        )

        val finalObstacles = obstacles + floorPosition

        val filteredObstacles = finalObstacles
            .filter { currentGrain.y + 1 == it.y }
            .filter {
                currentGrain.x - 1 == it.x ||
                    currentGrain.x == it.x ||
                    currentGrain.x + 1 == it.x
            }

        filteredObstacles.forEach { obstacle ->
            possiblePosition.remove(obstacle)
        }

        return possiblePosition.firstOrNull()
    }

    fun part1(input: List<String>): Int {
        val paths = extractPaths(input)
        val obstacles = fillPaths(paths).flatten().toMutableSet()
        val pathObstacles = obstacles.size
        val theAbyss = obstacles.maxOf { it.y } + 10
        var didNotFallIntoTheAbyss = true
        do {
            var currentGrain = Coord(500, 0)
            do {
                val canGoTo = canGoDownTo(currentGrain, obstacles.toList())
                if (canGoTo != null) {
                    currentGrain = canGoTo
                } else {
                    obstacles.add(currentGrain)
                }
            } while ( // EXIT : sand became stable OR sand is in abyss
                canGoTo != null && currentGrain.y < theAbyss
            )
            if (currentGrain.y >= theAbyss) didNotFallIntoTheAbyss = false
        } while (didNotFallIntoTheAbyss)
        return obstacles.size - pathObstacles
    }

    fun part2(input: List<String>): Int {
        val paths = extractPaths(input)
        val obstacles = fillPaths(paths).flatten().toMutableSet()
        val pathObstacles = obstacles.size
        val theFloor = obstacles.maxOf { it.y } + 2
        var lastGrainDidMove = true
        do {
            var currentGrain = Coord(500, 0)
            do {
                val floorPosition = listOf(
                    Coord(currentGrain.x, theFloor),
                    Coord(currentGrain.x - 1, theFloor),
                    Coord(currentGrain.x + 1, theFloor)
                )

                val canGoTo = canGoDownTo(currentGrain, obstacles.toList() + floorPosition)
                if (canGoTo != null) {
                    currentGrain = canGoTo
                } else {
                    obstacles.add(currentGrain)
                }
            } while ( // EXIT : sand became stable
                canGoTo != null
            )
            if (currentGrain == Coord(500, 0)) lastGrainDidMove = false
        } while (lastGrainDidMove)
        return obstacles.size - pathObstacles
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14_test")
    println("Test results:")
    // println(part1(testInput))
    //    check(part1(testInput) == 24)
    //
    println(part2(testInput))
    //    check(part2(testInput) == 93)

    val input = readInput("Day14")
    println("Final results:")
    // println(part1(input))
    //    check(part1(input) == 799)
    println(part2(input))
    //   check(part2(input) == 0)
    println("End")
}
