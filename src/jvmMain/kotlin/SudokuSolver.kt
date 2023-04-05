abstract class SudokuSolver(cell: Cell, grid: List<List<Cell>>){
    var grid: List<List<Cell>>
    var cell: Cell
//private var isSolved: Boolean = false
    init {
        this.grid = grid
    this.cell = cell
    }
    abstract fun checkRow(row: List<Cell>): Boolean
    abstract fun checkColumn(col: List<Cell>): Boolean
    abstract fun checkBox(box: List<List<Cell>>): Boolean

    fun getRow(): List<Cell>{
        return grid[cell.row]
    }
    fun getColumn(): List<Cell>{
        return grid.map { it.get(cell.col) }
    }
//    fun getBox(): List<List<Cell>>{
//        val boxRow = cellRow / 3
//        val boxCol = cellCol / 3
//        return grid.subList(boxRow * 3, boxRow * 3 + 3).map { it.subList(boxCol * 3, boxCol * 3 + 3) }
//    }
//    Template Method
    fun solve(): Boolean{
        return checkRow(getRow()) || checkColumn(getColumn())
    }



}