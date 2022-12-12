// ktlint-disable filename
package exercises

import readInput

data class Point(val x: Int, val y: Int, var visited: Boolean = false)

fun main() {
    fun findPoint(grid: List<String>, point: Char): Point {
        grid.forEachIndexed { y, line ->
            val x = line.indexOf(point)
            if (x != -1) return Point(x, y)
        }
        return Point(-1, -1)
    }

    fun getValidNeighbours(grid: List<String>, currentPosition: Point): MutableMap<Point, Char> {
        val result = mutableMapOf<Point, Char>()

        // make sure our neighbours coordinates are not out of bounds
        val left = (currentPosition.x - 1).coerceIn(0 until grid[0].length)
        val right = (currentPosition.x + 1).coerceIn(0 until grid[0].length)
        val top = (currentPosition.y - 1).coerceIn(grid.indices)
        val bottom = (currentPosition.y + 1).coerceIn(grid.indices)

        // get all neighbours
        result[currentPosition.copy(x = left)] = grid[currentPosition.y].elementAt(left)
        result[currentPosition.copy(x = right)] = grid[currentPosition.y].elementAt(right)
        result[currentPosition.copy(y = top)] = grid[top].elementAt(currentPosition.x)
        result[currentPosition.copy(y = bottom)] = grid[bottom].elementAt(currentPosition.x)

        // remove the neighbour that produced the same coordinates as current position
        result.remove(currentPosition)
        return result
    }

    fun part1(input: List<String>): Int {
        //val grid = mutableListOf<Point>().addAll(input.map {  })
        val currentPosition = findPoint(input, 'S')
        val toVisit = mutableListOf(currentPosition)
        var currentValue: Char
        val endPosition = findPoint(input, 'E')
        var steps = 0

        while (toVisit.isNotEmpty()) {
            steps++
            var nextToVisit = mutableSetOf<Point>()
            for (point in toVisit) {
                currentValue = input[point.y][point.x]
                if (currentValue == 'S') currentValue = 'a'
                // find neighbours
                val neighbours = getValidNeighbours(input, point)

                // find coordinates to go to
                nextToVisit =
                    neighbours
                    .filterKeys { !it.visited }
                    .filterValues { it >= currentValue && it - currentValue <= 1 }
                        .keys as MutableSet<Point>
                point.visited = true
            }
            if(nextToVisit.any { it == endPosition }) {
                return steps
            } else {
                toVisit.clear()
                toVisit.addAll(nextToVisit)
            }
        }

        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_test")
    println("Test results:")
    println(part1(testInput))
    // check(part1(testInput) == 31)
    // println(part2(testInput))
    // check(part2(testInput) == 0)

    val input = readInput("Day12")
    println("Final results:")
    println(part1(input))
    // check(part1(input) == 0)
    // println(part2(input))
    // check(part2(input) == 0)
}
