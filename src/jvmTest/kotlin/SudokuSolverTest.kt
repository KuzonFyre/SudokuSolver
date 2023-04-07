//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
import org.junit.Assert.assertEquals
import org.junit.Test

class SudokuSolverTest {

    private lateinit var grid: List<List<Cell?>>
    private lateinit var cell: Cell

    fun setUp() {
        // Prepare a sample grid for testing
        val n = 9
        grid = List(n) { i ->
            List(n) { j ->
                Cell("-", mutableSetOf(), i, j)
            }
        }

        // Prepare a sample cell for testing
        cell = grid[0][0]!!
    }

    class TestSudokuSolver(cell: Cell, grid: List<List<Cell?>>, n: Int) : SudokuSolver(cell, grid, n) {
        override fun checkRow(row: List<Cell?>) {}
        override fun checkColumn(col: List<Cell?>) {}
        override fun checkBox(box: List<List<Cell?>>) {}
    }

    @Test
    fun testGetRow() {
        this.setUp()
        val solver = TestSudokuSolver(cell, grid, 9)
        val row = solver.getRow()
        assertEquals(9, row.size)
        assertEquals(grid[0], row)
    }

    @Test
    fun testGetColumn() {
        this.setUp()
        val solver = TestSudokuSolver(cell, grid, 9)
        val column = solver.getColumn()
        assertEquals(9, column.size)
        for (i in 0 until 9) {
            assertEquals(grid[i][0], column[i])
        }
    }

    @Test
    fun testGetBox() {
        this.setUp()
        val solver = TestSudokuSolver(cell, grid, 9)
        val box = solver.getBox()
        assertEquals(3, box.size)
        assertEquals(3, box[0].size)

        for (i in 0 until 3) {
            for (j in 0 until 3) {
                assertEquals(grid[i][j], box[i][j])
            }
        }
    }
}
