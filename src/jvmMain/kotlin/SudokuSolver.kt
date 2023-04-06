import kotlin.math.sqrt

abstract class SudokuSolver(cell: Cell, grid: List<List<Cell?>>, n: Int) {
    var grid: List<List<Cell?>>
    var cell: Cell
    var n: Int
    init {
        this.grid = grid
        this.cell = cell
        this.n = n
    }
    abstract fun checkRow(row: List<Cell?>)
    abstract fun checkColumn(col: List<Cell?>)
    abstract fun checkBox(box: List<List<Cell?>>)

    fun setValue (){
        if (cell.potentialValues.size == 1) {
            cell.value = cell.potentialValues.first()
            cell.potentialValues.clear()
        }
    }
    fun getRow(): List<Cell?> {
        return grid[cell.row]
    }
    fun getColumn(): List<Cell?>{
        println("Current Row" + cell.row)
        val columns: List<Cell?> = List(n) { col ->
            grid[col][cell.row]
        }
        return columns
    }
    fun getBox(): List<List<Cell?>> {
        val size = sqrt(n.toDouble()).toInt()
        val startRow = size * (cell.row / size)
        val startCol = size * (cell.col / size)
        val box: List<List<Cell?>> = List(size) { row ->
            List(size) { col ->
                grid[startRow + row][startCol + col]
            }
        }
        return box
    }
//    Template Method
    fun solve(){
        checkBox(getBox())
        checkRow(getRow())
        println("ColumnB" + getColumn())
        checkColumn(getColumn())
        println("ColumnA" + getColumn())
        println("Current Cell: $cell")
        setValue()
    }



}