
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BruteSolverTest {

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
        val solver = BruteSolver(cell, grid, 9)
        grid[0][1]!!.value = "1"
        grid[0][2]!!.value = "2"
        solver.checkRow(solver.getRow())
        assertEquals(7, cell.potentialValues.size)
        assertEquals(false, cell.potentialValues.contains("1"))
        assertEquals(false, cell.potentialValues.contains("2"))
    }

    @Test
    fun testCheckColumn() {
        val solver = BruteSolver(cell, grid, 9)
        grid[1][0]!!.value = "1"
        grid[2][0]!!.value = "2"
        solver.checkColumn(solver.getColumn())
        assertEquals(7, cell.potentialValues.size)
        assertEquals(false, cell.potentialValues.contains("1"))
        assertEquals(false, cell.potentialValues.contains("2"))
    }

    @Test
    fun testCheckBox() {
        val solver = BruteSolver(cell, grid, 9)
        grid[1][1]!!.value = "1"
        grid[2][2]!!.value = "2"
        solver.checkBox(solver.getBox())
        assertEquals(7, cell.potentialValues.size)
        assertEquals(false, cell.potentialValues.contains("1"))
        assertEquals(false, cell.potentialValues.contains("2"))
    }
}
