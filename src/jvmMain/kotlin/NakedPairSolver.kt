class NakedPairSolver(cell: Cell, grid: List<List<Cell?>>, n: Int): SudokuSolver(cell,grid,n){
    override fun checkRow(row: List<Cell?>) {
        var removePair:Cell? = null
        for (c in row){
            if (c != null && c.value!= "-") {
                if(c.potentialValues.containsAll(cell.potentialValues) && cell.potentialValues.containsAll(c.potentialValues) && c.potentialValues.size == 2 && cell.potentialValues.size == 2){
                    removePair = c
                    break
                }
            }
        }
        if(removePair != null) {
            for (c in row) {
                if (c != removePair) {
                    c?.potentialValues?.removeAll(removePair.potentialValues)
                }
            }
        }
    }

    override fun checkColumn(col: List<Cell?>){
        var removePair:Cell? = null
        for (c in col){
            if (c != null && c.value!= "-") {
                if(c.potentialValues.containsAll(cell.potentialValues) && cell.potentialValues.containsAll(c.potentialValues) && c.potentialValues.size == 2 && cell.potentialValues.size == 2){
                    removePair = c
                    break
                }
            }
        }
        if(removePair != null) {
            for (c in col) {
                if (c != removePair) {
                    c?.potentialValues?.removeAll(removePair.potentialValues)
                }
            }
        }
    }

    override fun checkBox(box: List<List<Cell?>>){
        var removePair:Cell? = null
        for (row in box){
            for (c in row)
                if (c != null && c.value!= "-") {
                    if(c.potentialValues.containsAll(cell.potentialValues) && cell.potentialValues.containsAll(c.potentialValues) && c.potentialValues.size == 2 && cell.potentialValues.size == 2){
                        removePair = c
                        break
                    }
                }
        }
        if (removePair != null) {
            for (row in box) {
                for (c in row)
                    c?.potentialValues?.removeAll(removePair.potentialValues)
            }
        }
    }

}