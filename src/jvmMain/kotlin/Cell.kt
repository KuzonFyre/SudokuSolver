class Cell(value: String, potentialValues: MutableSet<String>) {
    var value: String
    var potentialValues: MutableSet<String> = mutableSetOf()

init {
    this.value = value
    this.potentialValues = potentialValues
}

}