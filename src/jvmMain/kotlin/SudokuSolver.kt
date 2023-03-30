abstract class SudokuSolver(cellRow: Int, cellCol: Int, grid: List<List<Cell>>, length: Int) {
    var cellRow: Int
    var cellCol: Int
    var grid: List<List<Cell>>
    var length: Int
//private var isSolved: Boolean = false
    init {
        this.cellRow = cellRow
        this.cellCol = cellCol
        this.grid = grid
        this.length = length
    }
    abstract fun checkRow(row: List<Cell>): Boolean
    abstract fun checkColumn(col: List<Cell>): Boolean
    abstract fun checkBox(box: List<List<Cell>>): Boolean

    fun getRow(): List<Cell>{
        return grid.get(cellRow)
    }
    fun getColumn(): List<Cell>{
        return grid.map { it.get(cellCol) }
    }
    fun getBox(): List<List<Cell>>{
        val boxRow = cellRow / 3
        val boxCol = cellCol / 3
        return grid.subList(boxRow * 3, boxRow * 3 + 3).map { it.subList(boxCol * 3, boxCol * 3 + 3) }
    }
//    Template Method
    fun solve(): Boolean{
        return checkRow(getRow()) || checkColumn(getColumn()) || checkBox(getBox())
    }



}