package io.github.ch8n.whatis.ui.screens.stories


import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Composable
fun StoriesScreen(navController: NavHostController) {
    TODO()
}


@Preview(
    showBackground = true,
    device = Devices.NEXUS_6P
)
@Composable
fun PreviewStoriesScreen() {
    StoriesScreen(NavHostController(LocalContext.current))
}
