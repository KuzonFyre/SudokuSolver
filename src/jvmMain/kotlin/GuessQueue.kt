object GuessQueue {
    private val queue = ArrayDeque<GuessData>()

    fun addGuess(grid: List<List<Cell?>>, guess: String) {
        queue.add(GuessData(grid, guess))
    }

    fun popGuess(): GuessData {
        val guess = queue.last()
        queue.removeLast()
        return guess
    }


}
data class GuessData(val grid: List<List<Cell?>>, val guess: String)