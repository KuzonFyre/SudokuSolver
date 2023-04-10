

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.io.File

class AppViewModel {
    val state = SudokuState()

    fun solve() {
        var solved = false
        var updated = false
        while(!solved) {
            updated = runRegSolve()
            while(!updated){
                if (GuessQueue.size()==0) {
                    guessCell()
                }else{
                    backTrackGrid()
                }

                updated = true
            }
            solved = isSolved()
        }
        state.isSolved = true
            printGrid()
        }

    private fun guessCell() {
        val cell = findFirst()
        val solver = cell?.let { GuessSolver(it, state._grid, state.n) }
        if (solver != null) {
            solver.setValue()
            copyGrid(solver)
            println(GuessQueue.toString())
            printGrid()
        }
    }

    private fun isUnsolvable(): Boolean {
        for (i in 0 until state.n) {
            for (j in 0 until state.n) {
                if (state._grid[i][j]?.value == "-" && state._grid[i][j]?.potentialValues?.isEmpty()!!) {
                    println("THIS VALUE IS EMPTY" + state._grid[i][j])
                        return true
                    }
                }
            }
        return false
    }
    private fun findFirst(): Cell? {
        for (i in 0 until state.n) {
            for (j in 0 until state.n) {
                if (state._grid[i][j]?.value == "-") {
                    print("Found empty cell at $i, $j")
                    return state._grid[i][j]
                }
            }
        }
        throw Exception("No empty cells")
    }
    private fun backTrackGrid(){
        val backTrackGrid = GuessQueue.popGuess()
        print("Before:")
        printGrid()
        for (i in 0 until state.n) {
            for (j in 0 until state.n) {
                state._grid[i][j] = backTrackGrid.grid[i][j]?.copy()
            }
        }
        print("After:")
        printGrid()
    }
    private fun runRegSolve(): Boolean {
        var updated = false
        for (i in 0 until state.n) {
            for (j in 0 until state.n) {
                var solver: SudokuSolver
                if (state._grid[i][j]?.value == "-") {
                    solver = state._grid[i][j]?.let { BruteSolver(it, state._grid, state.n) }!!
                    updated = solver.solve() || updated
                    copyGrid(solver)
                    solver = state._grid[i][j]?.let { NakedPairSolver(it, state._grid, state.n) }!!
                    updated = solver.solve() || updated
                    copyGrid(solver)
                    solver = state._grid[i][j]?.let { NakedTripleSolver(it, state._grid, state.n) }!!
                    updated = solver.solve() || updated
                    copyGrid(solver)

                }
            }
        }
        return updated
    }

    private fun isSolved(): Boolean {
        for (i in 0 until state.n) {
            for (j in 0 until state.n) {
                if (state._grid[i][j]?.value == "-") {
                    return false
                }
            }
        }
        return true
    }
    private fun copyGrid(solver: SudokuSolver){
        for (i in 0 until state.n) {
            for (j in 0 until state.n) {
                solver.grid[i][j]?.let { state._grid[i][j]?.copy() }
            }
        }
    }
    private fun printGrid() {
        for (i in 0 until state.n) {
            for (j in 0 until state.n) {
                print("${state._grid[i][j]} ")
            }
            println()
        }
    }

    fun exportData(fileName: String?) {
        val currentDir = System.getProperty("user.dir")
        val fileContent = File("$currentDir\\src\\jvmMain\\SamplePuzzles\\Output\\${fileName}").printWriter().use { out ->
            out.println(state.n)
            for (i in 0 until state.n) {
                for (j in 0 until state.n) {
                    out.print("${state._grid[i][j]?.value} ")
                }
                out.println()
            }
        }
    }

    fun setData(fileName: String?) {
        state.clearData()
        try {
            val currentDir = System.getProperty("user.dir")
            val fileContent = fileName?.let { File("$currentDir\\src\\jvmMain\\SamplePuzzles\\Input\\$it").readLines().filter {it1 ->  it1.isNotBlank() } }

            // read the grid size
            state.n = fileContent?.get(0)?.toInt()!!
            state.values = fileContent[1].split(" ").toMutableSet()
            print(fileContent.size)
            print(state.n + 2)
            if (fileContent.size != state.n + 2) {
                throw Exception("Invalid input: The number of lines in the file does not match the grid size.")
            }
            for (i in 0 until state.n) {
                val validate = fileContent[1].split(" ").toMutableSet()
                print("Validate: $validate")
                val row = fileContent[i + 2].split(" ")
                state._grid.add(mutableStateListOf())
                state._grid[i].clear()
                for (j in 0 until state.n) {
                    if(row[j] != "-") {
                        if(validate.remove(row[j])) state._grid[i].add(Cell(row[j], emptySet<String>().toMutableSet(), i, j))
                        else throw Exception("Invalid input")
                    }else{
                        val entries = fileContent[1].split(" ").toMutableSet()
                        state._grid[i].add(Cell(row[j], entries, i, j))
                    }
                }
            }
            state.isValidData = true
        } catch (e: Exception) {
            state.isValidData = false
        }
    }

}
class SudokuState {
    var n by mutableStateOf(0)
    var _grid = mutableStateListOf(mutableStateListOf<Cell?>())
    val grid: List<List<Cell?>> get() = _grid
    var isValidData by mutableStateOf(false)
    var values: MutableSet<String> = mutableSetOf()
    var selectedCell by mutableStateOf<Cell?>(null)
    var isSolved by mutableStateOf(false)

    fun clearData(){
        _grid.clear()
        isValidData = false
        n = 0
        values.clear()
        selectedCell = null
    }
}