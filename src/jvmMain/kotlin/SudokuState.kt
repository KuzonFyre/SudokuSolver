

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.io.File

class AppViewModel {
    val state = SudokuState()
    fun solve() {
//        var cell: Cell
        for (i in 0 until state.n) {
            for (j in 0 until state.n) {
                if (state._grid[i][j]?.value == "-") {
                    println("\nGrid:$state.grid")
                    val solver = state._grid[i][j]?.let { BruteSolver(it, state._grid) }
                    if (solver != null) {
                        println("Checking")
                        if (solver.solve()) {
                            println("Solved:" + (solver.grid[i][j]?.value))
                            solver.grid[i][j]?.value?.let { state._grid[i][j]?.copy(value = it) }
                        }
                    }
                }
            }
        }
        print("Solved Grid:${state._grid}")

    }
//25 by 25 901
    //TODO check for invalid input
    fun solveCell(){
        if (state.selectedCell?.value == "-") {
            val solver = BruteSolver(state.selectedCell!!, state._grid)
            println("Checking")
            if (solver.solve()) {
                //println("Solved:" + (solver.grid[i][j]?.value))
//                solver.cell.value.let { state.selectedCell?.copy(value = it) }
                solver.grid[state.selectedCell!!.row][state.selectedCell!!.col]?.value?.let { state._grid[state.selectedCell!!.row][state.selectedCell!!.col]?.copy(value = it) }
                //state._grid[state.selectedCell!!.row][state.selectedCell!!.col]?.copy(value = solver.cell.value)

                //solver.grid[i][j]?.value?.let { state._grid[i][j]?.copy(value = it) }
            }
        }
    }


    fun setData(fileName: String?) {
        println("Result $fileName")
        val currentDir = System.getProperty("user.dir")
        println("The current working directory is: $currentDir")
        val fileContent = fileName?.let { File("$currentDir\\src\\jvmMain\\SamplePuzzles\\Input\\$it").readLines() }

        // read the grid size
        state.n = fileContent?.get(0)?.toInt()!!
        state.values = fileContent[1].split(" ").toMutableSet()
        //state.grid.value = MutableList(state.n) { MutableList(state.n) { null } }
        // read the grid
        for (i in 0 until state.n) {
            val row = fileContent[i + 2].split(" ")
            state._grid.add(mutableStateListOf())
            for (j in 0 until state.n) {
                val entries = fileContent[1].split(" ").toMutableSet()
                state._grid[i].add(Cell(row[j], entries,i, j))
            }
        }
    }
}
class SudokuState {
        var n by mutableStateOf(0)
//       var grid by mutableStateListOf<List<Cell>>(mutableStateListOf<Cell?>())
        //var _grid by mutableStateOf(mutableListOf())
    var _grid = mutableStateListOf(mutableStateListOf<Cell?>())
    val grid: List<List<Cell?>> get() = _grid


    //var grid : List<Cell> get() = _grid; set(value) { _grid = value }
        var values: MutableSet<String> = mutableSetOf()
        var selectedCell by mutableStateOf<Cell?>(null)


}


//        var solver by BruteSolver(0, 0, grid.value.map(Array<Cell>::toList), n)