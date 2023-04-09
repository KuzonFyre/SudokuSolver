

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.io.File

class AppViewModel {
    val state = SudokuState()

    fun solve() {
        var solved = false
        var cellSolved: Boolean
        while(!solved) {
            for (i in 0 until state.n) {
                for (j in 0 until state.n) {
                    var solver: SudokuSolver
                    if (state._grid[i][j]?.value == "-") {
                        solver = state._grid[i][j]?.let { BruteSolver(it, state._grid, state.n) }!!
                        cellSolved = solver.solve()
                        copyGrid(solver)
                        solver = state._grid[i][j]?.let { NakedPairSolver(it, state._grid, state.n) }!!
                        cellSolved = solver.solve() || cellSolved
                        copyGrid(solver)
                        if (!cellSolved){
                            if (solver.solve()) {
                                copyGrid(solver)
                            } else {
                                backTrackGrid()
                            }
                        }
                        cellSolved = false
                    }
                }
            }
            printGrid()
            println(GuessQueue.toString())
            solved = isSolved()
        }

    }

    fun backTrackGrid(){
        val backTrackGrid = GuessQueue.popGuess()
        for (i in 0 until state.n) {
            for (j in 0 until state.n) {
                backTrackGrid.grid[i][j]?.value?.let { state._grid[i][j]?.copy(value = it) }
            }
        }
    }
//    fun runRegSolve() {
//        var updated = false
//        for (i in 0 until state.n) {
//            for (j in 0 until state.n) {
//                var solver: SudokuSolver
//                if (state._grid[i][j]?.value == "-") {
//                    solver = state._grid[i][j]?.let { BruteSolver(it, state._grid, state.n) }!!
//                    cellSolved = solver.solve()
//                    copyGrid(solver)
//                    solver = state._grid[i][j]?.let { NakedPairSolver(it, state._grid, state.n) }!!
//                    cellSolved = solver.solve() || cellSolved
//                    copyGrid(solver)
//                }
//            }
//        }
//    }

    fun isSolved(): Boolean {
        for (i in 0 until state.n) {
            for (j in 0 until state.n) {
                if (state._grid[i][j]?.value == "-") {
                    return false
                }
            }
        }
        return true
    }
    fun copyGrid(solver: SudokuSolver){
        for (i in 0 until state.n) {
            for (j in 0 until state.n) {
                solver.grid[i][j]?.value?.let { state._grid[i][j]?.copy(value = it) }
            }
        }
    }
    fun printGrid() {
        for (i in 0 until state.n) {
            for (j in 0 until state.n) {
                print("${state._grid[i][j]} ")
            }
            println()
        }
    }
    //25 by 25 901
    //TODO check for invalid input
    fun solveCell() {
        var solver: SudokuSolver
        if (state.selectedCell?.value == "-") {
            solver = BruteSolver(state.selectedCell!!, state._grid, state.n)
            solver.solve()
            copyGrid(solver)
            solver = NakedPairSolver(state.selectedCell!!, state._grid, state.n)
            solver.solve()
            copyGrid(solver)
//            solver = NakedTripleSolver(state.selectedCell!!, state._grid, state.n)
//            solver.solve()
//            copyGrid(solver)

        }
        //println("Value: " + state.selectedCell?.value)
        printGrid()
    }

    fun guessCell(){
            val solver: SudokuSolver = state._grid[state.selectedCell!!.row][state.selectedCell!!.col]?.let {
                GuessSolver(
                    it,
                    state._grid,
                    state.n
                )
            }!!
            if (solver.solve()) {
                copyGrid(solver)
            } else {
                val backTrackGrid = GuessQueue.popGuess()
                for (i in 0 until state.n) {
                    for (j in 0 until state.n) {
                        backTrackGrid.grid[i][j]?.value?.let { state._grid[i][j]?.copy(value = it) }
                    }
                }
//            }
//            solved = isSolved()
        }

    }

    fun setData(fileName: String?) {
        try {
            val currentDir = System.getProperty("user.dir")
            val fileContent = fileName?.let { File("$currentDir\\src\\jvmMain\\SamplePuzzles\\Input\\$it").readLines() }

            // read the grid size
            state.n = fileContent?.get(0)?.toInt()!!
            state.values = fileContent[1].split(" ").toMutableSet()
//            println("Values: ${state.values}")
//            println("N: ${state.n}")
            for (i in 0 until state.n) {
                val row = fileContent[i + 2].split(" ")
                state._grid.add(mutableStateListOf())
                state._grid[i].clear()
                val entries = fileContent[1].split(" ").toMutableSet()
                val validate = fileContent[1].split(" ").toMutableSet()
                for (j in 0 until state.n) {
                    if(row[j] != "-") {
                        if(validate.remove(row[j])) state._grid[i].add(Cell(row[j], emptySet<String>().toMutableSet(), i, j))
                        else throw Exception("Invalid input")
                    }else{
                        state._grid[i].add(Cell(row[j], entries, i, j))
                    }
                }
            }
            //printGrid()
            state.isValidData = true
        } catch (e: Exception) {
            println(e.message)
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