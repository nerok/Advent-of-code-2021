import java.io.File

fun main(args: Array<String>) {
    day10part2()
}

fun day10part1() {
    val input = File("day10input.txt").bufferedReader().readLines()
    var score = 0
    input.forEach{ line ->
        val stack = ArrayDeque<String>()
        var mismatchedBracket = ""
        line.split("").forEach { char ->
            if (mismatchedBracket != "") return@forEach
            when (char) {
                "(" -> stack.add(char)
                "[" -> stack.add(char)
                "{" -> stack.add(char)
                "<" -> stack.add(char)
                ")" -> if (stack.last() == "(") stack.removeLast() else {
                    println("$line - Expected ${stack.last()}, but found $char instead.")
                    mismatchedBracket = char
                }
                "]" -> if (stack.last() == "[") stack.removeLast() else {
                    println("$line - Expected ${stack.last()}, but found $char instead.")
                    mismatchedBracket = char
                }
                "}" -> if (stack.last() == "{") stack.removeLast() else {
                    println("$line - Expected ${stack.last()}, but found $char instead.")
                    mismatchedBracket = char
                }
                ">" -> if (stack.last() == "<") stack.removeLast() else {
                    println("$line - Expected ${stack.last()}, but found $char instead.")
                    mismatchedBracket = char
                }
            }
        }
        if (mismatchedBracket != "") {
            when (mismatchedBracket) {
                ")" -> score += 3
                "]" -> score += 57
                "}" -> score += 1197
                ">" -> score += 25137
            }
            println(score)
        }
    }
    println(score)
}

fun day10part2() {
    val input = File("day10input.txt").bufferedReader().readLines()
    val scores = input.map { line ->
        var score = 0L
        val stack = ArrayDeque<String>()
        line.split("").forEach { char ->
            when (char) {
                "(" -> stack.add(char)
                "[" -> stack.add(char)
                "{" -> stack.add(char)
                "<" -> stack.add(char)
                ")" -> if (stack.last() == "(") stack.removeLast() else {
                    return@map null
                }
                "]" -> if (stack.last() == "[") stack.removeLast() else {
                    return@map null
                }
                "}" -> if (stack.last() == "{") stack.removeLast() else {
                    return@map null
                }
                ">" -> if (stack.last() == "<") stack.removeLast() else {
                    return@map null
                }
            }
        }
        while (stack.isNotEmpty()) {
            score *= 5
            when (stack.removeLast()) {
                "(" -> score += 1
                "[" -> score += 2
                "{" -> score += 3
                "<" -> score += 4
            }
        }
        score
    }.filterNotNull().also { println(it) }.sorted()
    println(scores[scores.size/2])
}