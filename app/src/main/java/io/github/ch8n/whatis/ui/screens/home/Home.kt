package io.github.ch8n.whatis.ui.screens.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import io.github.ch8n.whatis.ui.navigation.Screen
import java.util.*


fun String.safeRandomIndex(): Int {
    val string = this
    if (string.isEmpty() || string.length < 2) return -1
    if (string.length == 2) return 0
    val randomChar = string.random()
    val indexOfChar = string.indexOf(randomChar)
    val isSafe = indexOfChar in 0..(length - 2)
    return if (isSafe) indexOfChar else safeRandomIndex()
}

val sampleNames = listOf<Pair<String, String>>(
    "Katrina" to "Kaif",
    "Divya" to "Gosh",
    "Akshay" to "Kumar",
    "Salman" to "Khan",
    "Priyanka" to "Chopra",
    "Ritik" to "Roshan",
    "Alia" to "Bhatt",
    "Amitabh" to "Bachchan",
    "Amir" to "Khan",
    "kareena" to "Kapoor",
    "Anunshka" to "Sharma",
    "Kangana" to "Ranaut",
    "Ranveer" to "Singh",
    "Aishwarya" to "Rai",
    "Varun" to "Dhawan",
)

@Composable
fun HomeScreen(navController: NavHostController) {

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val fullName = sampleNames.random()
                val firstName by remember { mutableStateOf(fullName.first) }
                val lastName by remember { mutableStateOf(fullName.second) }
                val firstRandomIndex by remember { mutableStateOf(firstName.safeRandomIndex()) }
                val secondRandomIndex by remember { mutableStateOf(lastName.safeRandomIndex()) }

                Text(
                    text = "If",
                    style = MaterialTheme.typography.h2,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Medium
                )

                if (firstName.length >= 7 || lastName.length >= 7) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row {
                            firstName.forEachIndexed { index, _char ->
                                Text(
                                    text = if (index == 0) _char.uppercaseChar()
                                        .toString() else _char.toString(),
                                    style = MaterialTheme.typography.h1,
                                    fontSize = 56.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = if (index == firstRandomIndex || index == firstRandomIndex + 1) {
                                        Modifier.border(width = 1.dp, color = Color.Red)
                                    } else {
                                        Modifier
                                    }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            lastName.forEachIndexed { index, _char ->
                                Text(
                                    text = _char.toString(),
                                    style = MaterialTheme.typography.h1,
                                    fontSize = 56.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = if (index == secondRandomIndex || index == secondRandomIndex + 1) {
                                        Modifier.border(width = 1.dp, color = Color.Red)
                                    } else {
                                        Modifier
                                    }
                                )
                            }
                        }

                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        firstName.forEachIndexed { index, _char ->
                            Text(
                                text = _char.toString(),
                                style = MaterialTheme.typography.h1,
                                fontSize = 42.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = if (index == firstRandomIndex || index == firstRandomIndex + 1) {
                                    Modifier.border(width = 1.dp, color = Color.Red)
                                } else {
                                    Modifier
                                }
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        lastName.forEachIndexed { index, _char ->
                            Text(
                                text = _char.toString(),
                                style = MaterialTheme.typography.h1,
                                fontSize = 42.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = if (index == firstRandomIndex || index == firstRandomIndex + 1) {
                                    Modifier.border(width = 1.dp, color = Color.Red)
                                } else {
                                    Modifier
                                }
                            )
                        }
                    }
                }

                Text(
                    text = "Is",
                    style = MaterialTheme.typography.h2,
                    fontWeight = FontWeight.Medium,
                    fontSize = 42.sp,
                )

                val name =
                    "${
                        firstName.get(firstRandomIndex)
                    }${
                        firstName.get(firstRandomIndex + 1)
                    }${
                        lastName.get(secondRandomIndex)
                    }${
                        lastName.get(secondRandomIndex + 1)
                    }".lowercase(Locale.getDefault())

                Text(
                    text = name.take(1).uppercase(Locale.getDefault()) + name.drop(1),
                    style = MaterialTheme.typography.h1,
                    fontSize = 56.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-36).dp),
            contentAlignment = Alignment.BottomCenter
        ) {

            OutlinedButton(onClick = {
                navController.navigate(Screen.NameForm.route)
            }) {
                Text(
                    text = "What's Yours?",
                    style = MaterialTheme.typography.h2,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }


}


@Preview(
    showBackground = true,
    device = Devices.NEXUS_6P
)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(NavHostController(LocalContext.current))
}
