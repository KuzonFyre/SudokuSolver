import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.FileDialog
import java.awt.Frame
import java.io.File

@Composable
@Preview
fun App() {
    var isFileChooserOpen by remember { mutableStateOf(false) }
//    var fileContent by remember { mutableStateOf<List<String>?>(null) }
    var grid by remember { mutableStateOf<Array<Array<String>>?>(null) }
    var n by remember { mutableStateOf(0) }
    if (isFileChooserOpen) {
        FileDialog {
            isFileChooserOpen = false
            println("Result $it")
            val currentDir = System.getProperty("user.dir")
            println("The current working directory is: $currentDir")
            val fileContent = it?.let { it1 -> File("$currentDir\\src\\jvmMain\\SamplePuzzles\\Input\\$it1").readLines() }

            // read the grid size

            n = fileContent?.get(0)?.toInt()!!

            // read the symbols
            val symbols = fileContent.get(1).split(" ")

            // read the grid
            grid = n.let { it1 -> Array(it1) { Array(n) { "" } } }
            for (i in 0 until n) {
                val row = fileContent[i + 2].split(" ")
                for (j in 0 until n) {
                    grid?.get(i)?.set(j, row[j])
                }
            }
            println(fileContent)
        }
    }
    MaterialTheme {
        Column {
            Row {
                Button(onClick = {
                    isFileChooserOpen = true
                }) {
                    Text("hello")
                }
            }
            Row {
                if (grid != null && n != 0) {
                    LazyVerticalGrid(modifier = Modifier.border(shape = RectangleShape, width = 1.dp, color = Color.Black), columns = GridCells.Fixed(n)) {
                        grid?.forEach { row ->
                            row.forEach { cell ->
                                item { Text(modifier = Modifier.border(shape = RectangleShape, width = 1.dp, color = Color.Black), text = cell) }
                            }
                        }
                    }
                }
            }
        }
    }
}


fun main() = application {

    Window(onCloseRequest = ::exitApplication) {
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
//b = bruteForceSolve()
//b.solve
