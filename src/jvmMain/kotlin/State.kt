import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.io.File

class AppViewModel {
    val state = State()
}
class State {
        var n by mutableStateOf(0)
        var grid by mutableStateOf(arrayOf(arrayOf<Cell>()))
        var symbols by mutableStateOf(mutableSetOf<String>())
        var selectedCell: Cell? = null

    fun solveCell(cell: Cell){
        selectedCell = cell
        val solver = BruteSolver(cell.row, cell.col, grid.map(Array<Cell>::toList), n)
        if (solver.solve()){
            grid = solver.grid.map(List<Cell>::toTypedArray).toTypedArray()
        }
    }

    fun setData(fileName: String?) {
        println("Result $fileName")
        val currentDir = System.getProperty("user.dir")
        println("The current working directory is: $currentDir")
        val fileContent = fileName?.let { File("$currentDir\\src\\jvmMain\\SamplePuzzles\\Input\\$it").readLines() }

        // read the grid size
        n = fileContent?.get(0)?.toInt()!!

        // read the symbols
        symbols = fileContent[1].split(" ").toMutableSet()

        // read the grid
        grid = Array(n) { Array(n) { Cell("-1",symbols,-1,-1) } }
        for (i in 0 until n) {
            val row = fileContent[i + 2].split(" ")
            for (j in 0 until n) {
                grid[i][j].value = row[j]
                grid[i][j].row = i
                grid[i][j].col = j
            }
        }

    }
}

//        var solver by BruteSolver(0, 0, grid.value.map(Array<Cell>::toList), n)