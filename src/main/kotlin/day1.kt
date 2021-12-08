import java.io.File

fun main(args: Array<String>) {
    day1part2()
}

fun day1part1() {
    val input = File("day1input.txt").bufferedReader().readLines()
    input.map { Integer.parseInt(it) }.windowed(2) {
        it.first() < it.last()
    }.count { it }.also { println(it) }
}

fun day1part2() {
    val input = File("day1input.txt").bufferedReader().readLines()
    input.map { Integer.parseInt(it) }.windowed(3) {
        it.sum()
    }.windowed(2){
        it.first() < it.last()
    }.count { it }.also { println(it) }
}