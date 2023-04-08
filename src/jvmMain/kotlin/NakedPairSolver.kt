class NakedPairSolver(cell: Cell, grid: List<List<Cell?>>, n: Int): SudokuSolver(cell,grid,n){
    private fun checkList(li: List<Cell?>){
        if(cell.potentialValues.size != 2) return
        var removePair:Cell? = null
        for (c in li){
            if (c != null && c != cell){
                if(c.potentialValues.containsAll(cell.potentialValues) && cell.potentialValues.containsAll(c.potentialValues) && c.potentialValues.size == 2){
                    print("Naked Pair found")
                    removePair = c
                    break
                }
            }
        }
        if(removePair != null) {
            for (c in li) {
                if (c != removePair && c != cell) {
                    c?.potentialValues?.removeAll(removePair.potentialValues)
                }
            }
        }
    }
    override fun checkRow(row: List<Cell?>) {
        checkList(row)
    }

    override fun checkColumn(col: List<Cell?>){
        checkList(col)
    }

    override fun checkBox(box: List<List<Cell?>>) {
        if (cell.potentialValues.size != 2) return
        var removePair: Cell? = null
        for (row in box) {
            for (c in row)
                if (c != null && c != cell) {
                    if (c.potentialValues.containsAll(cell.potentialValues) && cell.potentialValues.containsAll(c.potentialValues) && c.potentialValues.size == 2) {
                        removePair = c
                        break
                    }
                }
        }
        if (removePair != null) {
            for (row in box) {
                for (c in row) {
                    if (c != removePair && c != cell) {
                        c?.potentialValues?.removeAll(removePair.potentialValues)
                    }
                }
            }
        }
    }

}