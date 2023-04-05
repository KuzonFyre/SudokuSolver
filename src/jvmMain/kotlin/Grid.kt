import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Grid {
    var grid by mutableStateOf(mutableListOf(mutableListOf("")))

    init {

    }
    fun setCell(row: Int, col: Int, value: String) {
        grid[row][col] = value
    }

    fun getCell(row: Int, col: Int): String {
        return grid[row][col]
    }
}