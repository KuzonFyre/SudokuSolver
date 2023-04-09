class GuessSolver(cell: Cell, grid: List<List<Cell?>>, n: Int) : SudokuSolver(cell,grid,n){

    override fun checkRow(row: List<Cell?>){
        return
    }

    override fun checkColumn(col: List<Cell?>){
        return
    }

    override fun checkBox(box: List<List<Cell?>>){
        return
    }


    //Returns true if a guess was set
    //Returns false if no guess was set
    override fun setValue (): Boolean{

        if(cell.potentialValues.isEmpty()){
            return false
        }
        val value = cell.potentialValues.random()
        //cell.potentialValues.remove(value)
        val gridCopy = List(n) { row ->
            List(n) { col ->
                grid[row][col]?.copy()
            }
        }
        GuessQueue.addGuess(gridCopy, value)

        cell.value = value
        return true
    }

}


