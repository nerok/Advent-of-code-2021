import java.io.File

fun main(args: Array<String>) {
    day3part2()
}

fun day3part1() {
    val countArray = IntArray(12) { 0 }
    val input = File("day3input.txt").bufferedReader().readLines()
    input.map { it.mapIndexed { index, value ->
        if (value == '1') {
            countArray[index] = (countArray[index] + 1)
        }
        else {
            countArray[index] = (countArray[index] - 1)
        }
        }
    }
    countArray.forEach { print(if (it > 0) "1" else "0") }
}

fun day3part2() {
    val input = File("day3input.txt")
        .bufferedReader()
        .readLines()
        .map { line ->
            line.split("")
                .filter {
                    it.isNotEmpty()
                }
                .map { it.toInt() }
        }
    val majority = getMajority(input, 0)
    val highset = input.filter { it[0] == majority }
    val lowset = input.filter { it[0] != majority }
    val oxygenRating = higherReduction(highset)
    val CO2Rating = smallerReduction(lowset)
    val oxygenRatingDecimal = Integer.parseInt(oxygenRating.joinToString(separator = "") { it.toString() }, 2)
    val CO2RatingDecimal = Integer.parseInt(CO2Rating.joinToString(separator = "") { it.toString() }, 2)
    println(oxygenRatingDecimal)
    println(CO2RatingDecimal)
    println("${oxygenRatingDecimal*CO2RatingDecimal}")
}

private fun getMajority(input: List<List<Int>>, index: Int): Int {
    return input.map { it[index] }.let {
        if (it.sum()*2 == it.size) {
            1
        }
        else if (it.sum() == 2 && it.size == 3) {
            1
        }
        else if (it.sum() > it.size / 2) 1 else 0
    }
}

fun higherReduction(set: List<List<Int>>): List<Int> {
    var reductionSet = set
    var index = 1
    while (reductionSet.size > 1) {
        val majority = getMajority(reductionSet, index)
        reductionSet = reductionSet.filter { it[index] == majority }
        index += 1
    }
    return reductionSet.first()
}

fun smallerReduction(set: List<List<Int>>): List<Int> {
    var reductionSet = set
    var index = 1
    while (reductionSet.size > 1) {
        val majority = getMajority(reductionSet, index)
        reductionSet = reductionSet.filter { it[index] != majority }
        index += 1
    }
    return reductionSet.first()
}
