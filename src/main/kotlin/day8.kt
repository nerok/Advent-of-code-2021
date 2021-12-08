import java.io.File
import java.util.*

fun main(args: Array<String>) {
    day8part2()
}

fun day8part1() {
    val countArray = IntArray(8) { 0 }
    val input = File("day8input.txt").bufferedReader().readLines()
    input.map { line ->
        line.split(" | ").map { it.split(" ") }.let { it.first() to it.last() }
    }.map { entry ->
        entry.first.groupBy { it.length } to entry.second.groupBy { it.length }
    }.map { it.second }.forEach {
        it.forEach {
            countArray[it.key] += it.value.size
        }
    }
    val countMap = countArray.mapIndexed { index, i -> index to i }.toMap()
    println("Score: ${countMap[2]!!.plus(countMap[3]!!).plus(countMap[4]!!).plus(countMap[7]!!)}")
}

fun day8part2() {
    val countArray = IntArray(8) { 0 }
    val input = File("day8input.txt").bufferedReader().readLines()
    input.map { line ->
        line.split(" | ").map { it.split(" ") }.let { it.first().map { it.toSortedSet() } to it.last().map { it.toSortedSet() } }
    }.map {
        val map = predictPairs(it.first)
        Integer.parseInt(it.second.map { map[it] }.let { it.joinToString("") })
    }.also { println(it) }.also {
        println(it.sum())
    }
}

fun predictPairs(x: List<SortedSet<Char>>): MutableMap<SortedSet<Char>, Int> {
    val map = x.associateWith { -1 }.toMutableMap()
    map.forEach {
        when (it.key.size) {
            2 -> {
                map[it.key] = 1
            }
            3 -> {
                map[it.key] = 7
            }
            4 -> {
                map[it.key] = 4
            }
            7 -> {
                map[it.key] = 8
            }
            else -> {
            }
        }
    }
    map.filter { it.key.size == 6 }.forEach {
        if (it.key.containsAll(map.filterValues { it == 4 }.keys.first())) {
            map[it.key] = 9
        } else if (it.key.containsAll(map.filterValues { it == 1 }.keys.first())) {
            map[it.key] = 0
        } else {
            map[it.key] = 6
        }
    }
    map.filter { it.value == -1 }.forEach {
        if (it.key.containsAll(map.filterValues { it == 7 }.keys.first())) {
            map[it.key] = 3
        } else if (map.filterValues { it == 9 }.keys.first().containsAll(it.key)) {
            map[it.key] = 5
        } else {
            map[it.key] = 2
        }
    }
    return map
}
