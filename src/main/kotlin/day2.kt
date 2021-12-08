import java.io.File

fun main(args: Array<String>) {
    day2part2()
}

fun day2part1() {
    var depth = 0
    var horizontal = 0
    val input = File("day2input.txt").bufferedReader().readLines()
    input.forEach {
        val (command, value) = it.split(" ")
        when (command) {
            "forward" -> horizontal = horizontal.plus(value.toInt())
            "down" -> depth = depth.plus(value.toInt())
            "up" -> depth = depth.minus(value.toInt())
        }
    }
    println("Horizontal: $horizontal")
    println("Depth: $depth")
    println("Score: ${depth*horizontal}")
}

fun day2part2() {
    var depth = 0
    var horizontal = 0
    var aim = 0
    val input = File("day2input.txt").bufferedReader().readLines()
    input.forEach {
        val (command, value) = it.split(" ")
        when (command) {
            "forward" -> {
                horizontal = horizontal.plus(value.toInt())
                depth = depth.plus(aim.times(value.toInt()))
            }
            "down" -> aim = aim.plus(value.toInt())
            "up" -> aim = aim.minus(value.toInt())
        }
    }
    println("Horizontal: $horizontal")
    println("Depth: $depth")
    println("Score: ${depth*horizontal}")
}