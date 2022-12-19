// ktlint-disable filename
package exercises

import readInput

data class Valve(
    val name: String,
    val flowRate: Int,
    var isOpen: Boolean = false
)

fun main() {
    fun extractValves(input: List<String>): Pair<MutableList<Valve>, Map<String, List<String>>> {
        val pattern = "^Valve ([A-Z]{2}) .*=(\\d*);.*(valve|valves) (.*)\$".toRegex()
        val mapOfValves: MutableMap<String, MutableList<String>> = mutableMapOf()
        val valves: MutableList<Valve> = mutableListOf()
        for (line in input) {
            pattern.findAll(line).forEach { matchResult ->
                val valveName: String = matchResult.groupValues[1]
                val flowRate: Int = matchResult.groupValues[2].toInt()
                val leadToList: MutableList<String> =
                    matchResult.groupValues[4].split(",").map { it.trim() }.toMutableList()
                valves.add(Valve(name = valveName, flowRate = flowRate))
                mapOfValves[valveName] = leadToList
            }
        }

        return Pair(valves, mapOfValves)
    }

    fun listLeadTo(
        tunnels: Map<String, List<String>>,
        valveName: String,
        valves: MutableList<Valve>
    ) = tunnels[valveName]?.map { valveToName ->
        valves.find { it.name == valveToName }
    }

    fun part1(input: List<String>): Int {
        val (valves, tunnels) = extractValves(input)
        val leadTo = listLeadTo(tunnels, "DD", valves)
        var currentValve = valves[0]
        repeat(30) {
            val minute = it + 1
            if (currentValve.flowRate == 0) {
                // valve is stuck
            }
        }

        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day16_test")
    println("Test results:")
    println(part1(testInput))
    // check(part1(testInput) == 0)
    // println(part2(testInput))
    // check(part2(testInput) == 0)

    val input = readInput("Day16")
    // println("Final results:")
    // println(part1(input))
    // check(part1(input) == 0)
    // println(part2(input))
    // check(part2(input) == 0)
}
