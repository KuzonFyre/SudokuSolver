class GuessSolver(cell: Cell,grid: List<List<Cell?>>, n: Int) : SudokuSolver(cell,grid,n){


    override fun checkRow(row: List<Cell?>){
        return
    }

    override fun checkColumn(col: List<Cell?>){
        return
    }

    override fun checkBox(box: List<List<Cell?>>){
        return
    }


    override fun setValue (): Boolean{
        cell.value = cell.potentialValues.first()
        return true
    }

}