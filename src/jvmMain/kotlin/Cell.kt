data class Cell(var value: String, var potentialValues: MutableSet<String>, val row: Int, val col: Int, var guesses: MutableSet<String>){
    fun removePotentialValue(value: String){
        potentialValues.remove(value)
    }
}