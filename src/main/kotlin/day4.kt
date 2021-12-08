import java.io.File

fun main(args: Array<String>) {
    day4()
}

fun day4() {
    val input = File("day4input.txt").bufferedReader()
    val drawings = input.readLine().split(",").map { it.toInt() }
    input.readLines()
        .asSequence()
        .windowed(6, step = 6)
        .map { board ->
            board.filter { it.isNotEmpty() }
        }
        .map { board ->
            board.map { row ->
                row.split(" ")
                    .filter { it.isNotEmpty() }
                    .map { it.toInt() }
                    .toMutableList()
            }
        }
        .map { board ->
            calculateScore(board, drawings)
        }
        //.minByOrNull for part 1
        //.maxByOrNull for part 2
        .maxByOrNull { it.first }!!.also { println(it) }
}

fun calculateScore(bingoBoard: List<MutableList<Int>>, drawing: List<Int>): Pair<Int, Int> {
    drawing.forEachIndexed { drawNumber, draw ->
        bingoBoard.mapIndexed { index, ints ->
            if (ints.contains(draw)) {
                index to ints.indexOf(draw)
            }
            else {
                null
            }
        }.filterNotNull().forEach { coordinate ->
            bingoBoard[coordinate.first][coordinate.second] = 0
            if (bingoBoard[coordinate.first].none { it != 0 }) {
                return drawNumber to bingoBoard.sumOf { it.sum() } * drawing[drawNumber]
            }
            else if (bingoBoard.map { row -> row[coordinate.second] }.none { it != 0 }) {
                return drawNumber to bingoBoard.sumOf { it.sum() } * drawing[drawNumber]
            }
        }
    }
    return 0 to 0
}