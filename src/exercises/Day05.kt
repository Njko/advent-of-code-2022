// ktlint-disable filename
package exercises

import readInput

data class Move(val quantity: Int, val from: Int, val to: Int)

fun main() {
    fun extractStacks(lines: List<String>): List<MutableList<Char>> {
        val numberOfStacks = lines.last()[lines.last().length - 1].toString().toInt()

        val stacks = mutableListOf<MutableList<Char>>()
        for (u in 0 until numberOfStacks) {
            val stack = mutableListOf<Char>()
            stacks.add(stack)
        }

        for (i in lines.size - 2 downTo 0) {
            val numberOfBrackets = numberOfStacks * 2
            val numberOfSpaces = numberOfStacks - 1
            // fill the lines to have same size
            val line = lines[i].padEnd(numberOfStacks + numberOfBrackets + numberOfSpaces)
            for (j in line.indices) {
                if (!line[j].isWhitespace()) {
                    when (j) {
                        1 -> stacks[0].add(line[j])
                        line.length - 2 -> stacks[numberOfStacks - 1].add(line[j])
                        else -> {
                            if (j % 4 == 1) {
                                stacks[(j - 1) / 4].add(line[j])
                            }
                        }
                    }
                }
            }
        }
        return stacks.toList()
    }

    fun extractMoves(data: List<String>): List<Move> {
        val pattern = "move (\\d*) from (\\d*) to (\\d*)".toRegex()
        val moves = mutableListOf<Move>()
        for (line in data) {
            pattern.findAll(line).forEach { matchResult ->
                moves.add(
                    Move(
                        quantity = matchResult.groupValues[1].toInt(),
                        from = matchResult.groupValues[2].toInt(),
                        to = matchResult.groupValues[3].toInt()
                    )
                )
            }
        }
        return moves
    }

    fun executeMovesWithCrateMover9000(
        moves: List<Move>,
        stacks: List<MutableList<Char>>
    ) {
        for (move in moves) {
            val stackFrom = stacks[move.from - 1]
            val stackTo = stacks[move.to - 1]
            repeat(move.quantity) {
                stackTo.add(stackFrom.last())
                stackFrom.removeLast()
            }
        }
    }

    fun readStacks(stacks: List<MutableList<Char>>): String {
        var answer = ""
        for (stack in stacks) {
            answer += stack.last().toString()
        }
        return answer
    }

    fun part1(input: List<String>): String {
        val splitIndex = input.indexOfFirst { line -> line.isEmpty() }

        val stacks = extractStacks(input.subList(0, splitIndex))
        val moves = extractMoves(input.subList(splitIndex + 1, input.size))

        executeMovesWithCrateMover9000(moves, stacks)

        return readStacks(stacks)
    }

    fun executeMovesWithCrateMover9001(moves: List<Move>, stacks: List<MutableList<Char>>) {
        for (move in moves) {
            val stackFrom = stacks[move.from - 1]
            val stackTo = stacks[move.to - 1]
            val stackToMove = stackFrom.takeLast(move.quantity)
            stackTo.addAll(stackToMove)
            repeat(move.quantity) {
                stackFrom.removeLast()
            }
        }
    }

    fun part2(input: List<String>): String {
        val splitIndex = input.indexOfFirst { line -> line.isEmpty() }
        val stacks = extractStacks(input.subList(0, splitIndex))
        val moves = extractMoves(input.subList(splitIndex + 1, input.size))

        executeMovesWithCrateMover9001(moves, stacks)

        return readStacks(stacks)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    println("Test results:")
    println(part1(testInput))
    check(part1(testInput) == "CMZ")
    println(part2(testInput))
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println("Final results:")
    println(part1(input))
    check(part1(input) == "FCVRLMVQP")
    println(part2(input))
    check(part2(input) == "RWLWGJGFD")
}
