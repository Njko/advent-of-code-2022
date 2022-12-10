// ktlint-disable filename
package exercises

import readInput

// https://www.kodeco.com/books/data-structures-algorithms-in-kotlin/v1.0/chapters/6-trees

sealed class Node {
    data class Folder(val name: String, val children: MutableList<Node> = mutableListOf()) : Node()
    data class File(val name: String, val size: Int) : Node()
}

fun Node.Folder.add(child: Node) = this.children.add(child)

// https://www.youtube.com/watch?v=Q819VW8yxFo&t=2090s
fun main() {
    fun part1(input: List<String>): Int {
        /*var startRecording = false
        var currentDirectory = ""
        var root = Node.Folder("/")
        input.forEach { line ->
            if (line.startsWith("$ cd ")) {
                startRecording = false
                currentDirectory = line.last().toString()
                // read directory move
                return@forEach
            }
            if (line.startsWith("ls")) {
                startRecording = true
                return@forEach
            }
            if (startRecording) {

            }
        }*/

        val root = Node.Folder("/").apply {
            add(
                Node.Folder("a").apply {
                    add(
                        Node.Folder("e").apply {
                            add(Node.File("i", 584))
                        }
                    )
                    add(Node.File("f", 29116))
                    add(Node.File("g", 2557))
                    add(Node.File("h.lst", 62596))
                }
            )
            add(Node.File("b.txt", 14848514))
            add(Node.File("c.dat", 8504156))
            add(
                Node.Folder("d").apply {
                    add(Node.File("j", 4060174))
                    add(Node.File("d.log", 8033020))
                    add(Node.File("d.ext", 5626152))
                    add(Node.File("k", 7214296))
                }
            )
        }
        val folders = root.children.filterIsInstance<Node.Folder>().toList()
        println("$root")
        println("$folders")
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    println("Test results:")
    println(part1(testInput))
    check(part1(testInput) == 23)
    println(part2(testInput))
    check(part2(testInput) == 23)

    val input = readInput("Day07")
    println("Final results:")
    println(part1(input))
    check(part1(input) == 1046)
    println(part2(input))
    check(part2(input) == 1046)
}
