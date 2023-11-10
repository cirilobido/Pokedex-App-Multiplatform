import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.window.application
import moe.tlaster.precompose.PreComposeWindow

fun main() = application {
    PreComposeWindow(
        onCloseRequest = ::exitApplication,
        title = "Pokedex Multiplatform",
    ) {
        MainView()
    }
}