import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    val lines = File("day5input.txt").reader()
    var coordinates = mutableListOf<List<Pair<Int, Int>>>()
    var size = 0
    lines.forEachLine { fileLine ->
        fileLine
            .split("->")
            .map { coordinates ->
                coordinates
                    .split(",")
                    .map { coordinate ->
                        Integer.parseInt(coordinate.filter { !it.isWhitespace() }).also { if (it > size) { size = it } }
                    }.let {
                        it.first() to it.last()
                    }
            }.let { coordinatePair ->
                if (coordinatePair.first().first == coordinatePair.last().first) {
                    val lowerEnd = minOf(coordinatePair.first().second, coordinatePair.last().second)
                    val higherEnd = maxOf(coordinatePair.first().second, coordinatePair.last().second)
                    return@let IntRange(lowerEnd, higherEnd).map {
                       coordinatePair.first().first to it
                    }
                }
                else if (coordinatePair.first().second == coordinatePair.last().second) {
                    val lowerEnd = minOf(coordinatePair.first().first, coordinatePair.last().first)
                    val higherEnd = maxOf(coordinatePair.first().first, coordinatePair.last().first)
                    return@let IntRange(lowerEnd, higherEnd).map {
                        it to coordinatePair.first().second
                    }
                }
                else {
                    val firstDistance = abs(coordinatePair.first().first - coordinatePair.last().first)
                    val secondDistance = abs(coordinatePair.first().second - coordinatePair.last().second)
                    if (firstDistance == secondDistance) {
                        val lowerFirst = minOf(coordinatePair.first().first, coordinatePair.last().first)
                        val higherFirst = maxOf(coordinatePair.first().first, coordinatePair.last().first)
                        val firstRange = IntRange(lowerFirst, higherFirst).toList()
                        val lowerSecond = minOf(coordinatePair.first().second, coordinatePair.last().second)
                        val higherSecond = maxOf(coordinatePair.first().second, coordinatePair.last().second)
                        val secondRange = IntRange(lowerSecond, higherSecond).toList()
                        val reverseFirst = coordinatePair.first().first != firstRange.first()
                        val reverseSecond = coordinatePair.first().second != secondRange.first()
                        return@let firstRange.mapIndexed { index, i ->
                            if (reverseFirst xor reverseSecond) {
                                i to secondRange[secondDistance - index]
                            } else {
                                i to secondRange[index]
                            }
                        }
                    }
                    else {
                        return@let emptyList()
                    }
                }
            }.let { coordinates.add(it) }

    }
    var area = Array(size+1) { IntArray(size+1) { 0 } }
    coordinates.flatten().forEach {
        area[it.second][it.first] = area[it.second][it.first] + 1
    }

    val hotpoints = area.map { it.filter { it > 1 } }.flatten().count().also { println(it) }
}