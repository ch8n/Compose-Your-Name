package io.github.ch8n.whatis.ui.screens.nameReveal


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import io.github.ch8n.whatis.ui.screens.home.safeRandomIndex
import kotlinx.coroutines.launch
import whatis.R
import java.util.*

data class ShareItem(
    val icon: Int,
    val name: String
)

val shareOptions = listOf(
    ShareItem(
        icon = R.drawable.crystal_ball,
        name = "Share"
    ),
    ShareItem(
        icon = R.drawable.crystal_ball,
        name = "What is Community"
    ),
)


@ExperimentalMaterialApi
@Composable
fun NameRevealScreen(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val firstName by remember { mutableStateOf("Chetan") }
    val lastName by remember { mutableStateOf("Gupta") }
    var firstRandomIndex by remember { mutableStateOf(firstName.safeRandomIndex()) }
    var secondRandomIndex by remember { mutableStateOf(lastName.safeRandomIndex()) }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(modifier = Modifier.padding(8.dp)) {
                    Text(text = data.message)
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { _innerPadding ->

        val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetContent = {
                Column {
                    val (isLoading, setLoading) = remember { mutableStateOf(false) }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        val currentContext = LocalContext.current
                        shareOptions.forEachIndexed { index, _share ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .clickable {
                                        //TODO start activity
                                    }
                            ) {
                                Icon(
                                    painter = painterResource(id = _share.icon),
                                    contentDescription = "",
                                    tint = Color.Unspecified
                                )
                                Text(text = _share.name)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    if (isLoading) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Preparing Image...")
                            Spacer(modifier = Modifier.height(6.dp))
                            LinearProgressIndicator()
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        ) {
            Box(
                modifier = Modifier
                    .padding(_innerPadding)
                    .fillMaxSize()
                    .wrapContentSize()
            ) {

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

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
                                        fontSize = 56.sp,
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



                        Text(
                            text = "Is",
                            style = MaterialTheme.typography.h2,
                            fontWeight = FontWeight.Medium,
                            fontSize = 42.sp,
                        )

                        val name = "${
                            firstName.get(firstRandomIndex)
                        }${
                            firstName.get(firstRandomIndex + 1)
                        }${
                            lastName.get(secondRandomIndex)
                        }${
                            lastName.get(secondRandomIndex + 1)
                        }".lowercase(Locale.getDefault()).run {
                            take(1).uppercase(Locale.getDefault()) + drop(1)
                        }

                        Text(
                            text = name,
                            style = MaterialTheme.typography.h1,
                            fontSize = 56.sp,
                            fontWeight = FontWeight.SemiBold,
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            horizontalArrangement = Arrangement.Center
                        ) {

                            IconButton(onClick = {
                                firstRandomIndex = firstName.safeRandomIndex()
                                secondRandomIndex = lastName.safeRandomIndex()
                            }) {
                                Icon(imageVector = Icons.Rounded.Refresh, contentDescription = "")
                            }

                            val clipboardManager = LocalClipboardManager.current
                            IconButton(onClick = {
                                clipboardManager.setText(buildAnnotatedString {
                                    append("$firstName $lastName is $name")
                                })
                                scope.launch {
                                    scaffoldState.snackbarHostState
                                        .showSnackbar(
                                            message = "Copied to Clipboard...",
                                            duration = SnackbarDuration.Short
                                        )
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.ContentCopy,
                                    contentDescription = ""
                                )
                            }

                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(y = (-36).dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    OutlinedButton(onClick = {
                        scope.launch { bottomSheetState.show() }
                    }) {
                        Text(
                            text = "Share?",
                            style = MaterialTheme.typography.h2,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

    }
}


@ExperimentalMaterialApi
@Preview(
    showBackground = true,
    device = Devices.NEXUS_6P
)
@Composable
fun PreviewNameRevealScreen() {
    NameRevealScreen(NavHostController(LocalContext.current))
}
