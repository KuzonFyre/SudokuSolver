import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.io.File

class AppViewModel {
    val state = State()
}
class State {
        var n by mutableStateOf(0)
        var grid by mutableStateOf(mutableListOf(mutableListOf<Cell?>()))
        var values: MutableSet<String> = mutableSetOf()
        var selectedCell by mutableStateOf<Cell?>(null)
        var selectedValue by mutableStateOf("")

    fun solve(){

        for (i in 0 until n){
            for (j in 0 until n){
                if (grid[i][j]?.value == "-") {
                    println("\nGrid:$grid")
                    val solver = grid[i][j]?.let { BruteSolver(it, (grid as List<List<Cell>>)) }
                    if (solver != null) {
                        println("Checking")
                        if (solver.solve()) {
                            println("Solved:" + solver.grid[i][j].value)
                        }
                    }
                }
            }
        }
        print("Solved Grid:$grid")
    }
    fun solveCell(): String{
        if (selectedCell?.value == "-"){
            val solver = BruteSolver(selectedCell!!, grid as List<List<Cell>>)
            println("Checking")
            if (solver.solve()) {
                println("Solved:" + selectedCell!!.value)
            }
        }

        return selectedCell?.value ?: ""
    }


    fun setData(fileName: String?) {
        println("Result $fileName")
        val currentDir = System.getProperty("user.dir")
        println("The current working directory is: $currentDir")
        val fileContent = fileName?.let { File("$currentDir\\src\\jvmMain\\SamplePuzzles\\Input\\$it").readLines() }

        // read the grid size
        n = fileContent?.get(0)?.toInt()!!
        values = fileContent[1].split(" ").toMutableSet()

        println("grid:$grid")
        grid = MutableList(n) {MutableList(n) {null }}
        // read the grid
        for (i in 0 until n) {
            val row = fileContent[i + 2].split(" ")
            for (j in 0 until n) {
                val entries = fileContent[1].split(" ").toMutableSet()
                grid[i][j] = Cell(row[j], entries, i, j)
            }
        }
        println("grid:$grid")
//        grid.forEach { row ->
//            row.forEach { cell ->
//                print(cell)
//            }
//            println()
//        }
    }
}

//        var solver by BruteSolver(0, 0, grid.value.map(Array<Cell>::toList), n)