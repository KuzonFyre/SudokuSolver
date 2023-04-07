import org.junit.Assert.*
import org.junit.Test

class AppViewModelTest {

    private lateinit var appViewModel: AppViewModel

    fun setUp() {
        appViewModel = AppViewModel()
    }

    @Test
    fun testSolve() {
        this.setUp()
        appViewModel.setData("samplePuzzle.txt") // Provide a sample puzzle file
        appViewModel.solve()
        assertTrue(appViewModel.state.isSolved) // The actual solution should be verified against a correct solution
    }

    @Test
    fun testSolveCell() {
        this.setUp()
        appViewModel.setData("samplePuzzle.txt") // Provide a sample puzzle file
        appViewModel.state.selectedCell = appViewModel.state._grid[0][0] // Select the first cell
        appViewModel.solveCell()
        assertNotEquals("-", appViewModel.state.selectedCell?.value) // Verify that the selected cell has been updated
    }

    @Test
    fun testSetData() {
        this.setUp()
        print(System.getProperty("user.dir"))
        appViewModel.setData("samplePuzzle.txt") // Provide a sample puzzle file

        assertTrue(appViewModel.state.isValidData)
        assertEquals(4, appViewModel.state.n) // Assuming samplePuzzle.txt is a 4x4 Sudoku puzzle
        assertEquals(4, appViewModel.state.grid[0].size)
    }

    @Test
    fun testSetDataInvalidFileName() {
        this.setUp()
        appViewModel.setData("nonExistentFile.txt")
        assertFalse(appViewModel.state.isValidData)
    }
}
