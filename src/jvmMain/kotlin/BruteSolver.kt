class BruteSolver(cell: Cell, grid: List<List<Cell?>>, n: Int) : SudokuSolver(cell,grid,n){

    override fun checkRow(row: List<Cell?>): Boolean {
        for (c in row){
            if (c != null) {
                cell.removePotentialValue(c.value)
            }
        }
        return setValue()
    }


    override fun checkColumn(col: List<Cell?>): Boolean {
        for (c in col){
            if (c != null) {
                cell.potentialValues.remove(c.value)
            }
        }
        return setValue()
    }

    override fun checkBox(box: List<List<Cell?>>): Boolean {
        for (row in box){
            for (c in row){
                if (c != null) {
                    cell.potentialValues.remove(c.value)
                }
            }
        }
        return setValue()
    }


}