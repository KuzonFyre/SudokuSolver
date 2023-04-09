object GuessQueue {
    private val queue = ArrayDeque<GuessData>()

    fun addGuess(grid: List<List<Cell?>>, guess: String) {
        queue.add(GuessData(grid, guess))
    }

    fun size(): Int {
        return queue.size
    }
    fun popGuess(): GuessData {
        val guess = queue.last()
        queue.removeLast()
        return guess
    }

    override fun toString(): String {
        return "Guess Queue$queue"
    }

}
data class GuessData(val grid: List<List<Cell?>>, val guess: String)