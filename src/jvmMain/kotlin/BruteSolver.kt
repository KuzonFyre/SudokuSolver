import kotlin.math.sqrt

class BruteSolver(cellRow: Int, cellCol: Int, grid: List<List<Cell>>, length: Int) : SudokuSolver(cellRow,cellCol,grid, length){
    private var cell: Cell = grid[cellRow][cellCol]
    fun setValue ():Boolean{
        if (cell.potentialValues.size == 1) {
            cell.value = cell.potentialValues.first()
            return true
        }
        return false
    }
    override fun checkRow(row: List<Cell>): Boolean {
        for (i in row.indices){
            println(row[i].value)
            println(cell.potentialValues.toString())
            if (cell.potentialValues.contains(row[i].value)){
                cell.potentialValues.remove(row[i].value)
            }
        }
        cell.potentialValues.forEach { println(it) }
        return setValue()
    }

    override fun checkColumn(col: List<Cell>): Boolean {
        for (i in col.indices){
            if (cell.potentialValues.contains(col[i].value)){
                cell.potentialValues.remove(col[i].value)
            }
        }
        return setValue()
    }

    override fun checkBox(box: List<List<Cell>>): Boolean {
        for (i in box.indices){
            for (j in box[i].indices){
                if (cell.potentialValues.contains(box[i][j].value)){
                    cell.potentialValues.remove(box[i][j].value)
                }
            }
        }
        return setValue()
    }


}