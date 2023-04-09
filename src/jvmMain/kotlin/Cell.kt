data class Cell(var value: String, var potentialValues: MutableSet<String>, val row: Int, val col: Int) {
    fun removePotentialValue(value: String){

        potentialValues.remove(value)
    }
}