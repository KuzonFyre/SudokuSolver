import java.util.Collections.emptyList

class NakedTripleSolver(cell: Cell, grid: List<List<Cell?>>, size: Int): SudokuSolver(cell,grid,size){
    override fun checkRow(row: List<Cell?>): Boolean {
        if (!checkSize(cell)){ return false }
        val triple: MutableList<Cell?> = emptyList()
        for(c in row){
            if (c != null && c!= cell && checkSize(c)){
                triple.add(c)
            }
        }
        if(triple.size != 3){
            return false
        }

        val removeSet = (triple[2]?.let { (triple[1]?.let { triple[0]?.potentialValues?.union(it.potentialValues) })?.union(it.potentialValues) })
        if (removeSet != null) {
            if (removeSet.size != 3){
                return false
            }
            for (c in row){
                if (c != null && c != cell && c != triple[0] && c != triple[1] && c != triple[2]){
                    c.potentialValues.removeAll(removeSet)
                }
            }
            return true
        }
        return false
    }

    override fun checkColumn(col: List<Cell?>): Boolean {
        if (!checkSize(cell)){ return false }
        val triple: MutableList<Cell?> = emptyList()
        for(c in col){
            if (c != null && c!= cell && checkSize(c)){
                triple.add(c)
            }
        }
        if(triple.size != 3){
            return false
        }

        val removeSet = (triple[2]?.let { (triple[1]?.let { triple[0]?.potentialValues?.union(it.potentialValues) })?.union(it.potentialValues) })
        if (removeSet != null) {
            if (removeSet.size != 3){
                return false
            }
            for (c in col){
                if (c != null && c != cell && c != triple[0] && c != triple[1] && c != triple[2]){
                    c.potentialValues.removeAll(removeSet)
                }
            }
            return true
        }
        return false
    }
    override fun checkBox(box: List<List<Cell?>>): Boolean {
        if (!checkSize(cell)){ return false }
        val triple: MutableList<Cell?> = emptyList()
        for(row in box) {
            for (c in row) {
                if (c != null && c != cell && checkSize(c)) {
                    triple.add(c)
                }
            }
        }
        if(triple.size != 3){
            return false
        }

        val removeSet = (triple[2]?.let { (triple[1]?.let { triple[0]?.potentialValues?.union(it.potentialValues) })?.union(it.potentialValues) })
        if (removeSet != null) {
            if (removeSet.size != 3){
                return false
            }
            for (row in box){
                for (c in row) {
                    if (c != null && c != cell && c != triple[0] && c != triple[1] && c != triple[2]) {
                        c.potentialValues.removeAll(removeSet)
                    }
                }
            }
            return true
        }
        return false
    }

    fun checkSize(c: Cell): Boolean {
        return c.potentialValues.size == 2 || c.potentialValues.size == 3
    }
}

