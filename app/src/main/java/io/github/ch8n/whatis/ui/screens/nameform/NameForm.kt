package io.github.ch8n.whatis.ui.screens.nameform


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import io.github.ch8n.whatis.ui.navigation.Screen
import whatis.R
import java.util.*


@Composable
fun NameFormScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {

        val (firstNameText, setFirstName) = remember { mutableStateOf("") }
        val (lastNameText, setLastName) = remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    state = rememberScrollState(),
                    enabled = true
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "First Name?",
                style = MaterialTheme.typography.h2,
                fontSize = 42.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .wrapContentHeight(),
                placeholder = {
                    Text(text = "Name")
                },
                onValueChange = {
                    setFirstName(it)
                },
                value = firstNameText,
                singleLine = true,
                isError = firstNameText.length < 3
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Last Name?",
                style = MaterialTheme.typography.h2,
                fontSize = 42.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .wrapContentHeight(),
                placeholder = {
                    Text(text = "Surname")
                },
                onValueChange = {
                    setLastName(it)
                },
                value = lastNameText,
                singleLine = true,
                isError = lastNameText.length < 3
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-36f).dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            OutlinedButton(onClick = {
                if (firstNameText.length > 3 && lastNameText.length > 3) {
                    navController.navigate(
                        Screen.NameReveal.withArgs(
                            "firstName" to firstNameText.lowercase(Locale.getDefault())
                                .let {
                                    it.first().uppercaseChar() + it.drop(1)
                                },
                            "lastName" to lastNameText.lowercase(Locale.getDefault())
                                .let {
                                    it.first().uppercaseChar() + it.drop(1)
                                }
                        )
                    )
                }

            }) {
                Icon(
                    painter = painterResource(id = R.drawable.crystal_ball),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(width = 24.dp, height = 24.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Uncover",
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
fun PreviewNameFormScreen() {
    NameFormScreen(NavHostController(LocalContext.current))
}
