data class Cell(var value: String, var potentialValues: MutableSet<String>, val row: Int, val col: Int) {
    fun removePotentialValue(value: String):Boolean{
        return potentialValues.remove(value)
    }
    fun removeAllPotentialValues(){
        potentialValues.clear()
    }
}