// ktlint-disable filename
package exercises

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var cycle = 1
        var x = 1
        val cyclesToCheck = mutableMapOf(20 to 0, 60 to 0, 100 to 0, 140 to 0, 180 to 0, 220 to 0)
        input.forEach { instruction ->
            if (instruction != "noop") {
                cycle += 1
                cyclesToCheck.keys.contains(cycle).let {
                    if (it) {
                        cyclesToCheck[cycle] = cycle * x
                    }
                }
                val (_, value) = instruction.split(" ")
                x += value.toInt()
            }
            cycle += 1
            cyclesToCheck.keys.contains(cycle).let {
                if (it) {
                    cyclesToCheck[cycle] = cycle * x
                }
            }
        }
        return cyclesToCheck.values.sum()
    }

    fun drawOnScreenAtPosition(screen: MutableList<String>, cycle: Int, spritePosition: Int) {
        val cycleIndex: Int = cycle - 1
        val lineIndex: Int = cycleIndex / 40
        val pixelIndex: Int = cycleIndex % 40
        val spriteRange = spritePosition until spritePosition + 3

        val line = screen[lineIndex].toCharArray()
        line[pixelIndex] = if (spriteRange.contains(pixelIndex)) '#' else '.'
        screen[lineIndex] = String(line)
    }

    fun part2(input: List<String>) {
        val screen = mutableListOf(
            "........................................",
            "........................................",
            "........................................",
            "........................................",
            "........................................",
            "........................................"
        )

        var spritePosition = 0
        var cycle = 1
        input.forEach { instruction ->
            drawOnScreenAtPosition(screen, cycle, spritePosition)
            if (instruction != "noop") {
                cycle += 1
                drawOnScreenAtPosition(screen, cycle, spritePosition)
                val (_, value) = instruction.split(" ")
                spritePosition += value.toInt()
            }
            cycle += 1
        }
        println(screen.joinToString(separator = "\n"))
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    println("Test results:")
    println(part1(testInput))
    check(part1(testInput) == 13140)
    println(part2(testInput))

    val input = readInput("Day10")
    println("Final results:")
    println(part1(input))
    check(part1(input) == 13860)
    println(part2(input))
}
