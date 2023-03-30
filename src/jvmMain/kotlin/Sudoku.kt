import java.io.File
class Sudoku(inputFile: String) {
    private val grid: Array<Array<String>>

    init {
        val lines = File(inputFile).readLines()

        // read the grid size
        val n = lines[0].toInt()

        // read the symbols
        val symbols = lines[1].split(" ")

        // read the grid
        grid = Array(n) { Array(n) { "" } }
        for (i in 0 until n) {
            val row = lines[i + 2].split(" ")
            for (j in 0 until n) {
                grid[i][j] = row[j]
            }
        }
    }

    fun printGrid() {
        for (row in grid) {
            for (cell in row) {
                print("$cell ")
            }
            println()
        }
    }
}