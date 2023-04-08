import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NakedPairSolverTest {

    private lateinit var grid: List<List<Cell?>>
    private lateinit var cell: Cell

    @Before
    fun setUp() {
        // Prepare a sample grid for testing
        val n = 9
        grid = List(n) { i ->
            List(n) { j ->
                Cell("-", mutableSetOf("1", "2", "3", "4", "5", "6", "7", "8", "9"), i, j)
            }
        }

        // Prepare a sample cell for testing
        cell = grid[0][0]!!
    }

    @Test
    fun testCheckRow() {
        val solver = NakedPairSolver(cell, grid, 9)
        grid[0][1]!!.potentialValues = mutableSetOf("1", "2")
        cell.potentialValues = mutableSetOf("1", "2")
        print(solver.getRow())
        solver.checkRow(solver.getRow())
        solver.setValue()
        println(grid)
        for (i in 3 until 9) {
            assertEquals(false, grid[0][i]!!.potentialValues.contains("1"))
            assertEquals(false, grid[0][i]!!.potentialValues.contains("2"))
        }
    }

    @Test
    fun testCheckColumn() {
        val solver = NakedPairSolver(cell, grid, 9)
        grid[1][0]!!.potentialValues = mutableSetOf("1", "2")
        grid[2][0]!!.potentialValues = mutableSetOf("1", "2")
        cell.potentialValues = mutableSetOf("1", "2")
        solver.checkColumn(solver.getColumn())
        for (i in 3 until 9) {
            assertEquals(false, grid[i][0]!!.potentialValues.contains("1"))
            assertEquals(false, grid[i][0]!!.potentialValues.contains("2"))
        }
    }

    @Test
    fun testCheckBox() {
        val solver = NakedPairSolver(cell, grid, 9)
        grid[1][1]!!.potentialValues = mutableSetOf("1", "2")
        grid[2][2]!!.potentialValues = mutableSetOf("1", "2")
        cell.potentialValues = mutableSetOf("1", "2")
        solver.checkBox(solver.getBox())
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if ((i != 0 || j != 0) && (i != 1 || j != 1) && (i != 2 || j != 2)) {
                    assertEquals(false, grid[i][j]!!.potentialValues.contains("1"))
                    assertEquals(false, grid[i][j]!!.potentialValues.contains("2"))
                }
            }
        }
    }
}
