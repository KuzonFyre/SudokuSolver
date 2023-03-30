class BruteSolver(cellRow: Int, cellCol: Int, grid: List<List<Cell>>) : SudokuSolver(cellRow,cellCol,grid){
    private var cell: Cell = grid[cellRow][cellCol]
    fun setValue ():Boolean{
        if (cell.potentialValues.size == 1) {
            cell.value = cell.potentialValues.first()
            return true
        }
        return false
    }
    override fun checkRow(row: List<Cell>): Boolean {
        for (i in 0..8){
            if (cell.potentialValues.contains(row[i].value)){
                cell.potentialValues.remove(row[i].value)
            }
        }
        return setValue()
    }

    override fun checkColumn(col: List<Cell>): Boolean {
        for (i in 0..8){
            if (cell.potentialValues.contains(col[i].value)){
                cell.potentialValues.remove(col[i].value)
            }
        }
        return setValue()
    }

    override fun checkBox(box: List<List<Cell>>): Boolean {
        for (i in 0..2){
            for (j in 0..2){
                if (cell.potentialValues.contains(box[i][j].value)){
                    cell.potentialValues.remove(box[i][j].value)
                }
            }
        }
        return setValue()
    }


}