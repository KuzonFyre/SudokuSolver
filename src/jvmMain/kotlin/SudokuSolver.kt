abstract class SudokuSolver(cell: Cell, grid: List<List<Cell?>>, size: Int) {
    var grid: List<List<Cell?>>
    var cell: Cell
    var size: Int

    //private var isSolved: Boolean = false
    init {
        this.grid = grid
        this.cell = cell
        this.size = size
    }
    abstract fun checkRow(row: List<Cell?>): Boolean
    abstract fun checkColumn(col: List<Cell?>): Boolean
    abstract fun checkBox(box: List<List<Cell?>>): Boolean

    fun getRow(): List<Cell?> {
        return grid[cell.row]
    }
    fun getColumn(): List<Cell?>{

        return grid.map { it[cell.col] }
    }
    fun getBox(): List<List<Cell?>> {
        val startRow = size * (cell.row / size)
        val startCol = size * (cell.col / size)
        val box: List<List<Cell?>> = List(size) { row ->
            List(size) { col ->
                print("Row: ${startRow + row} Col: ${startCol + col}  ")
                grid[startRow + row][startCol + col]
            }
        }
        return box
    }
//    Template Method
    fun solve(): Boolean{
        return checkBox(getBox()) || checkRow(getRow())
    }



}