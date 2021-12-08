fun main(args: Array<String>) {
    day6part2()
}

fun day6part1() {
    var fish = listOf(3,4,3,1,2)
    val days = 80
    for (i in 1 .. days) {
        val numberOfZeros = fish.count { it == 0 }
        val nextFish = fish.map { if (it == 0) 7 else it }.map { it - 1 }
        fish = nextFish.let { it.plus(IntArray(numberOfZeros) { 8 }.toList()) }
    }
    println(fish.count())
}
fun day6part2() {
    val fish = listOf(5,1,1,4,1,1,4,1,1,1,1,1,1,1,1,1,1,1,4,2,1,1,1,3,5,1,1,1,5,4,1,1,1,2,2,1,1,1,2,1,1,1,2,5,2,1,2,2,3,1,1,1,1,1,1,1,1,5,1,1,4,1,1,1,5,4,1,1,3,3,2,1,1,1,5,1,1,4,1,1,5,1,1,5,1,2,3,1,5,1,3,2,1,3,1,1,4,1,1,1,1,2,1,2,1,1,2,1,1,1,4,4,1,5,1,1,3,5,1,1,5,1,4,1,1,1,1,1,1,1,1,1,2,2,3,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,5,1,1,1,1,4,1,1,1,1,4,1,1,1,1,3,1,2,1,2,1,3,1,3,4,1,1,1,1,1,1,1,5,1,1,1,1,1,1,1,1,4,1,1,2,2,1,2,4,1,1,3,1,1,1,5,1,3,1,1,1,5,5,1,1,1,1,2,3,4,1,1,1,1,1,1,1,1,1,1,1,1,5,1,4,3,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,3,3,1,2,2,1,4,1,5,1,5,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,5,1,1,1,4,3,1,1,4)
    var fishCount = fish.groupBy { it }.map { it.key.toLong() to it.value.count().toULong() }.toMap().toSortedMap()
    val days = 256
    for (i in 1 .. days) {
        val numberOfZeros = fishCount.getOrDefault(0, 0u)
        fishCount = fishCount.map { it.key - 1L to it.value }.toMap().toMutableMap().let {
            it[8] = numberOfZeros
            it[6] = it.getOrDefault(6, 0u).plus(numberOfZeros)
            it
        }.filterKeys { it >= 0 }.toSortedMap()
    }
    println(fishCount)
    println(fishCount.values.sum())
}