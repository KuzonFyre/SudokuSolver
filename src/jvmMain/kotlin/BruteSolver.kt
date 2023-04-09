class BruteSolver(cell: Cell, grid: List<List<Cell?>>, n: Int) : SudokuSolver(cell,grid,n){

    override fun checkRow(row: List<Cell?>){
        for (c in row){
            if (c != null) {
                cell.removePotentialValue(c.value)
                //if (c.potentialValues.size == 0 && c.value=="-") throw Exception("No potential values")
            }
        }
    }


    override fun checkColumn(col: List<Cell?>){
        for (c in col){
            if (c != null) {
                //println(c.value)
                cell.removePotentialValue(c.value)
                //if (c.potentialValues.size == 0 && c.value=="-") throw Exception("No potential values")
            }
        }
    }

    override fun checkBox(box: List<List<Cell?>>){
        for (row in box){
            for (c in row){
                if (c != null) {
                    cell.removePotentialValue(c.value)
                    //if (c.potentialValues.size == 0 && c.value=="-") throw Exception("No potential values")
                }
            }
        }
    }


}