import kotlin.math.sqrt

abstract class SudokuSolver(cell: Cell, grid: List<List<Cell?>>, n: Int) {
    var grid: List<List<Cell?>>
    var cell: Cell
    var n: Int

    //private var isSolved: Boolean = false
    init {
        this.grid = grid
        this.cell = cell
        this.n = n
    }
    abstract fun checkRow(row: List<Cell?>): Boolean
    abstract fun checkColumn(col: List<Cell?>): Boolean
    abstract fun checkBox(box: List<List<Cell?>>): Boolean

    fun setValue ():Boolean{
        if (cell.potentialValues.size == 1) {
            cell.value = cell.potentialValues.first()
            return true
        }
        return false
    }
    fun getRow(): List<Cell?> {
        return grid[cell.row]
    }
    fun getColumn(): List<Cell?>{
        val columns: List<Cell?> = List(n) { col ->
            grid[cell.row][col]
        }
        return columns
    }
    fun getBox(): List<List<Cell?>> {
        val size = sqrt(n.toDouble()).toInt()
        val startRow = size * (cell.row / size)
        val startCol = size * (cell.col / size)
        val box: List<List<Cell?>> = List(size) { row ->
            List(size) { col ->
                //print("Row: ${startRow + row} Col: ${startCol + col}  ")
                grid[startRow + row][startCol + col]
            }
        }
        return box
    }
//    Template Method
    fun solve(): Boolean{
        checkBox(getBox())
        checkRow(getRow())
        checkColumn(getColumn())
        return setValue()
    }



}