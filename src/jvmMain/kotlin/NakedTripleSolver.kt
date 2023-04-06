class NakedTripleSolver(cell: Cell, grid: List<List<Cell>>, size: Int): SudokuSolver(cell,grid,size){
    override fun checkRow(row: List<Cell?>): Boolean {
        var removeTriple: List<Cell?>? = null
        for (c in row) {
            if (c != null && c.potentialValues.size <= 3) {
                val triple = row.filter { it != c && it != null && it.potentialValues == c.potentialValues }
                print(triple)
                if (triple.size == 2 && triple.flatMap { it?.potentialValues ?: setOf() }.distinct().size == 2) {
                    removeTriple = triple + c
                    break
                }
            }
        }
        if (removeTriple == null) {
            return false
        }
        for (c in row) {
            if (c !in removeTriple && c != null) {
                c.potentialValues.removeAll(removeTriple.flatMap { it?.potentialValues ?: emptyList() })
            }
        }
        return true
    }

    override fun checkColumn(col: List<Cell?>): Boolean {
        TODO("Not yet implemented")
    }

    override fun checkBox(box: List<List<Cell?>>): Boolean {
        TODO("Not yet implemented")
    }
}