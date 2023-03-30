class Cell(value: String, potentialValues: MutableSet<String>, row: Int, col: Int) {
    var value: String
    var potentialValues: MutableSet<String>
    var row: Int
    var col: Int
init {
    this.value = value
    this.potentialValues = potentialValues
    this.row = row
    this.col = col
}

    override fun toString(): String {
        return value
    }
}