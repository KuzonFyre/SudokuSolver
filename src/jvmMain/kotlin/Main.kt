
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import java.awt.FileDialog
import java.awt.Frame


@Composable
@Preview
fun App() {
    val viewModel: AppViewModel = remember { AppViewModel() }
    val state = viewModel.state
    var isFileChooserOpen by remember { mutableStateOf(false) }
    if (isFileChooserOpen) {
        FileDialog {
            isFileChooserOpen = false
            if (it != null) {
                viewModel.setData(it)
            }
        }
    }
    if (state.isValidData) {
        MaterialTheme {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.1f)) {
                    Column {
                        Button(onClick = {
                            isFileChooserOpen = true
                        }) {
                            Text("Select Board")
                        }
                    }
                    Column {
                        Button(onClick = {
                            viewModel.solve()
                        }) {
                            Text("Solve grid")
                        }
                    }
                    Column {
                        Button(onClick = {
                            viewModel.solveCell()
                        }) {
                            Text("Solve Cell")
                        }
                    }
                }
                Row(modifier = Modifier.fillMaxSize()) {
                    LazyVerticalGrid(
                        modifier = Modifier.border(
                            shape = RectangleShape,
                            width = 1.dp,
                            color = Color.Black
                        ).fillMaxSize(), columns = GridCells.Fixed(state.n)
                    ) {
                        items(state.n * state.n) {
                            val row = it / state.n
                            val col = it % state.n
                            val cell = state.grid[row][col]
                            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.border(
                                shape = RectangleShape,
                                width = 1.dp,
                                color = Color.Black
                            ).padding(vertical = 10.dp, horizontal = 0.dp)
                                .selectable(selected = state.selectedCell == cell, onClick = {
                                    state.selectedCell = cell
                                })
                            )
                            {
                                if (cell != null) {
                                    Text(cell.value)
                                }
                            }
                        }
                    }
                }
            }
        }
    }else{
        MaterialTheme {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.1f)) {
                    Column {
                        Button(onClick = {
                            isFileChooserOpen = true
                        }) {
                            Text("Select Board")
                        }
                    }
                }
            }
        }
    }
}


fun main() = application {
    Window(state = WindowState(size = DpSize(500.dp,500.dp)), onCloseRequest = ::exitApplication) {
        App()
    }
}

@Composable
private fun FileDialog(
    parent: Frame? = null,
    onCloseRequest: (result: String?) -> Unit
) = AwtWindow(
    create = {
        object : FileDialog(parent, "Choose a file", LOAD) {
            override fun setVisible(value: Boolean) {
                super.setVisible(value)
                if (value) {
                    onCloseRequest(file)
                }
            }
        }
    },
    dispose = FileDialog::dispose
)
