class BruteSolver(cell: Cell, grid: List<List<Cell>>) : SudokuSolver(cell,grid){
    fun setValue ():Boolean{
        if (cell.potentialValues.size == 1) {
            grid
            cell.value = cell.potentialValues.first()
            return true
        }
        return false
    }
    override fun checkRow(row: List<Cell>): Boolean {
        //println("potential Values:" + cell.potentialValues)
        for (c in row){
            cell.removePotentialValue(c.value)
        }
        return setValue()
    }


    override fun checkColumn(col: List<Cell>): Boolean {
        for (cell in col){
            cell.potentialValues.remove(cell.value)
        }
        return setValue()
    }

    override fun checkBox(box: List<List<Cell>>): Boolean {
        for (i in box.indices){
            for (j in box[i].indices){
            }
        }
        return setValue()
    }


}