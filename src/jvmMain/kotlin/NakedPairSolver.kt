class NakedPairSolver(cell: Cell, grid: List<List<Cell?>>, n: Int): SudokuSolver(cell,grid,n){
    override fun checkRow(row: List<Cell?>): Boolean {
        var removePair:Cell? = null
        for (c in row){
            if (c != null) {
                if(c.potentialValues.containsAll(cell.potentialValues) && cell.potentialValues.containsAll(c.potentialValues) && c.potentialValues.size == 2 && cell.potentialValues.size == 2){
                    removePair = c
                    break
                }
            }
        }
        if(removePair == null){
            return false
        }
        for (c in row){
            if (c != removePair) {
                c?.potentialValues?.removeAll(removePair.potentialValues)
            }
        }
        return true
    }

    override fun checkColumn(col: List<Cell?>): Boolean {
        var removePair:Cell? = null
        for (c in col){
            if (c != null) {
                if(c.potentialValues.containsAll(cell.potentialValues) && cell.potentialValues.containsAll(c.potentialValues) && c.potentialValues.size == 2 && cell.potentialValues.size == 2){
                    removePair = c
                    break
                }
            }
        }
        if(removePair == null){
            return false
        }
        for (c in col){
            if (c != removePair) {
                c?.potentialValues?.removeAll(removePair.potentialValues)
            }
        }
        return true
    }

    override fun checkBox(box: List<List<Cell?>>): Boolean {
        var removePair:Cell? = null
        for (row in box){
            for (c in row)
                if (c != null) {
                    if(c.potentialValues.containsAll(cell.potentialValues) && cell.potentialValues.containsAll(c.potentialValues) && c.potentialValues.size == 2 && cell.potentialValues.size == 2){
                        removePair = c
                        break
                    }
                }
        }
        if (removePair == null){
            return false
        }
        for (row in box){
            for (c in row)
                c?.potentialValues?.removeAll(removePair.potentialValues)
        }
        return true
    }

}