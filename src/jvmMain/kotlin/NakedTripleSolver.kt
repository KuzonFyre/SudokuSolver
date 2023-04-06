import java.util.Collections.emptyList

class NakedTripleSolver(cell: Cell, grid: List<List<Cell?>>, size: Int): SudokuSolver(cell,grid,size){
    override fun checkRow(row: List<Cell?>): Boolean {
        if (!checkSize()){ return false }
        val triple: MutableList<Cell?> = emptyList()
        for(c in row){
            if (c != null && c!= cell && (c.potentialValues.size == 2 || c.potentialValues.size ==3)){
                triple.add(c)
            }
        }
        if(triple.size != 3){
            return false
        }

        cell.potentialValues union cell.potentialValues

        return false

//        for (c in row) {
//            if (c != null && c.potentialValues.size <= 3) {
////                if(c.potentialValues.containsAll(cell.potentialValues) && cell.potentialValues.containsAll(c.potentialValues) && c.potentialValues.size == 2 && cell.potentialValues.size == 2){
////                    removePair = c
////                    break
////                }
//
//                print("Triple$triple")
//                if (triple.size == 2 && triple.flatMap { it?.potentialValues ?: setOf() }.distinct().size == 2) {
//                    removeTriple = triple + c
//                    break
//                }
//            }
//        }
//        if (removeTriple == null) {
//            return false
//        }
//        for (c in row) {
//            if (c !in removeTriple && c != null) {
//                c.potentialValues.removeAll(removeTriple.flatMap { it?.potentialValues ?: emptyList() })
//            }
//        }
    }

    override fun checkColumn(col: List<Cell?>): Boolean {
        return false
    }

    override fun checkBox(box: List<List<Cell?>>): Boolean {
        return false
    }

    fun checkSize(): Boolean {
        return cell.potentialValues.size == 2 || cell.potentialValues.size == 3
    }
}

