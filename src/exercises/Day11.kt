// ktlint-disable filename
package exercises

import readInput

data class Monkey(
    val itemsToInspect: MutableList<Long>,
    val computeWorryLevel: (Long) -> Long,
    val testDivisibleBy: Long,
    val testTrueThrowTo: Int,
    val testFalseThrowTo: Int,
    var itemsInspected: Long = 0
)

val testMonkeys = listOf(
    Monkey(
        itemsToInspect = mutableListOf(79, 98),
        computeWorryLevel = { it * 19 },
        testDivisibleBy = 23,
        testTrueThrowTo = 2,
        testFalseThrowTo = 3
    ),
    Monkey(
        itemsToInspect = mutableListOf(54, 65, 75, 74),
        computeWorryLevel = { it + 6 },
        testDivisibleBy = 19,
        testTrueThrowTo = 2,
        testFalseThrowTo = 0
    ),
    Monkey(
        itemsToInspect = mutableListOf(79, 60, 97),
        computeWorryLevel = { it * it },
        testDivisibleBy = 13,
        testTrueThrowTo = 1,
        testFalseThrowTo = 3
    ),
    Monkey(
        itemsToInspect = mutableListOf(74),
        computeWorryLevel = { it + 3 },
        testDivisibleBy = 17,
        testTrueThrowTo = 0,
        testFalseThrowTo = 1
    )
)

val monkeys = listOf(
    Monkey(
        itemsToInspect = mutableListOf(66, 59, 64, 51),
        computeWorryLevel = { it * 3 },
        testDivisibleBy = 2,
        testTrueThrowTo = 1,
        testFalseThrowTo = 4
    ),
    Monkey(
        itemsToInspect = mutableListOf(67, 61),
        computeWorryLevel = { it * 19 },
        testDivisibleBy = 7,
        testTrueThrowTo = 3,
        testFalseThrowTo = 5
    ),
    Monkey(
        itemsToInspect = mutableListOf(86, 93, 80, 70, 71, 81, 56),
        computeWorryLevel = { it + 2 },
        testDivisibleBy = 11,
        testTrueThrowTo = 4,
        testFalseThrowTo = 0
    ),
    Monkey(
        itemsToInspect = mutableListOf(94),
        computeWorryLevel = { it * it },
        testDivisibleBy = 19,
        testTrueThrowTo = 7,
        testFalseThrowTo = 6
    ),
    Monkey(
        itemsToInspect = mutableListOf(71, 92, 64),
        computeWorryLevel = { it + 8 },
        testDivisibleBy = 3,
        testTrueThrowTo = 5,
        testFalseThrowTo = 1
    ),
    Monkey(
        itemsToInspect = mutableListOf(58, 81, 92, 75, 56),
        computeWorryLevel = { it + 6 },
        testDivisibleBy = 5,
        testTrueThrowTo = 3,
        testFalseThrowTo = 6
    ),
    Monkey(
        itemsToInspect = mutableListOf(82, 98, 77, 94, 86, 81),
        computeWorryLevel = { it + 7 },
        testDivisibleBy = 17,
        testTrueThrowTo = 7,
        testFalseThrowTo = 2
    ),
    Monkey(
        itemsToInspect = mutableListOf(54, 95, 70, 93, 88, 93, 63, 50),
        computeWorryLevel = { it + 4 },
        testDivisibleBy = 13,
        testTrueThrowTo = 2,
        testFalseThrowTo = 0
    )
)

fun main() {
    fun part1(monkeys: List<Monkey>): Long {
        repeat(20) {
            monkeys.forEach { monkey ->
                monkey.itemsToInspect.forEach { item ->
                    val worryLevel = monkey.computeWorryLevel.invoke(item) / 3

                    if (worryLevel % monkey.testDivisibleBy == 0L) {
                        monkeys[monkey.testTrueThrowTo].itemsToInspect.add(worryLevel)
                    } else {
                        monkeys[monkey.testFalseThrowTo].itemsToInspect.add(worryLevel)
                    }
                }
                monkey.itemsInspected += monkey.itemsToInspect.size
                monkey.itemsToInspect.clear()
            }
        }

        val orderedMonkeys = monkeys.sortedByDescending { it.itemsInspected }.take(2)
        return orderedMonkeys[0].itemsInspected * orderedMonkeys[1].itemsInspected
    }

    fun part2(monkeys: List<Monkey>): Long {
        val maxStress = monkeys.fold(1L) { acc, monkey -> acc * monkey.testDivisibleBy }

        repeat(10000) {
            monkeys.forEach { monkey ->
                monkey.itemsToInspect.forEach { item ->
                    val worryLevel = monkey.computeWorryLevel.invoke(item) % maxStress

                    if (worryLevel % monkey.testDivisibleBy == 0L) {
                        monkeys[monkey.testTrueThrowTo].itemsToInspect.add(worryLevel)
                    } else {
                        monkeys[monkey.testFalseThrowTo].itemsToInspect.add(worryLevel)
                    }
                }
                monkey.itemsInspected += monkey.itemsToInspect.size
                monkey.itemsToInspect.clear()
            }
        }

        val orderedMonkeys = monkeys.sortedByDescending { it.itemsInspected }.take(2)
        return orderedMonkeys[0].itemsInspected * orderedMonkeys[1].itemsInspected
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    println("Test results:")
    // println(part1(testMonkeys))
    // check(part1(testMonkeys) == 10605)
    println(part2(testMonkeys))
    // check(part2(testMonkeys) == 2713310158)

    val input = readInput("Day11")
    println("Final results:")
    // println(part1(monkeys))
    // check(part1(input) == 90294)
    println(part2(monkeys))
    // check(part2(input) != 18290327112)
}
