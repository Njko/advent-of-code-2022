// ktlint-disable filename
package exercises

import readInput

data class Blueprint(
    val index: Int,
    val oreRobotCost: Int,
    val clayRobotCost: Int,
    val obsidianRobotCost: Pair<Int, Int>,
    val geodeRobotConst: Pair<Int, Int>
)

fun extractBlueprints(input: List<String>): List<Blueprint> {
    val pattern =
        "^Blueprint (\\d*).*ore robot costs (\\d*).*clay robot costs (\\d*).*obsidian robot costs (\\d*) ore and (\\d*).*geode robot costs (\\d*) ore and (\\d*).*\$".toRegex()
    val blueprints = mutableListOf<Blueprint>()
    for (line in input) {
        pattern.findAll(line).forEach { matchResult ->
            val index = matchResult.groupValues[1].toInt()
            val oreRobotCost = matchResult.groupValues[2].toInt()
            val clayRobotCost = matchResult.groupValues[3].toInt()
            val obsidianRobotCostInOre = matchResult.groupValues[4].toInt()
            val obsidianRobotCostInClay = matchResult.groupValues[5].toInt()
            val geodeRobotCostInOre = matchResult.groupValues[6].toInt()
            val geodeRobotCostInClay = matchResult.groupValues[7].toInt()
            blueprints.add(
                Blueprint(
                    index,
                    oreRobotCost,
                    clayRobotCost,
                    Pair(obsidianRobotCostInOre, obsidianRobotCostInClay),
                    Pair(geodeRobotCostInOre, geodeRobotCostInClay)
                )
            )
        }
    }
    return blueprints.toList()
}

fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("DayTemplate_test")
    println("Test results:")
    println(part1(testInput))
    check(part1(testInput) == 0)
    println(part2(testInput))
    check(part2(testInput) == 0)

    val input = readInput("DayTemplate")
    println("Final results:")
    println(part1(input))
    check(part1(input) == 0)
    println(part2(input))
    check(part2(input) == 0)
}
