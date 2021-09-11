package io.github.ch8n.whatis.ui.screens.stories


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import whatis.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun StoriesScreen(navController: NavHostController) {
    val firstName: String = "Chetan"
    val lastName: String = "Gupta"

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val firstChar = firstName.random()
        val secondChar = firstName.random()
        val thirdChar = lastName.random()
        val fourthChar = lastName.random()

        Text(text = "Your Name")
        Row {
            firstName.forEach {
                if (firstChar == it || secondChar == it){
                    Text(text = "$it",modifier = Modifier.border(1.dp, Color.Red))
                }else{
                    Text(text = "$it")
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            lastName.forEach {
                if (thirdChar == it || fourthChar == it){
                    Text(text = "$it",modifier = Modifier.border(1.dp, Color.Red))
                }else{
                    Text(text = "$it")
                }
            }
        }
        Text(text = "is")
        val name = "$firstChar$secondChar$thirdChar$fourthChar".toLowerCase()

        Row {
            Text(text = name.take(1).toUpperCase()+name.drop(1))
            Spacer(modifier = Modifier.width(6.dp))
            IconButton(onClick = { /*TODO*/ }) {
                Text(text = "Speak")
            }
        }

        Spacer(modifier = Modifier.height(56.dp))
        Button(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.crystal_ball),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.size(width = 24.dp, height = 24.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Try again?")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Divider(color = Color.LightGray)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Share")
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(text = "Whatsapp")
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Instagram")
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Facebook")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Share in What is? Community")
    }
}


@Preview(
    showBackground = true,
    device = Devices.NEXUS_6P
)
@Composable
fun PreviewStoriesScreen() {
    StoriesScreen(NavHostController(LocalContext.current))
}
