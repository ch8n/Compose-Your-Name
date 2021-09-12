package io.github.ch8n.whatis.ui.screens.nameform


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import io.github.ch8n.whatis.ui.service.AppAnalytics
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

        LaunchedEffect(key1 = Unit) {
            AppAnalytics.log(
                "FormName",
                "Action" to "Screen_visit",
                "fullName" to firstNameText,
                "nickName" to lastNameText
            )
        }

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
                isError = firstNameText.length < 2,
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
                isError = lastNameText.length < 2
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-36f).dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            OutlinedButton(onClick = {

                AppAnalytics.log(
                    "NameForm",
                    "Action" to "Name_Submitted",
                    "firstNameText" to firstNameText.trim(),
                    "firstNameText" to lastNameText.trim()
                )

                if (firstNameText.length > 2 && lastNameText.length > 2) {
                    navController.navigate(
                        Screen.NameReveal.withArgs(
                            "firstName" to firstNameText.lowercase(Locale.getDefault())
                                .let {
                                    (it.first().uppercaseChar() + it.drop(1)).trim()
                                },
                            "lastName" to lastNameText.lowercase(Locale.getDefault())
                                .let {
                                    (it.first().uppercaseChar() + it.drop(1)).trim()
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

//
//fun loadAd(context: Context, adConfig: AdConfig) {
//    val adRequest: AdRequest = AdRequest.Builder().build()
//
//    InterstitialAd.load(context, "ca-app-pub-3940256099942544/1033173712", adRequest,
//        object : InterstitialAdLoadCallback() {
//            override fun onAdLoaded(interstitialAd: InterstitialAd) {
//                adConfig.ads = interstitialAd
//                adConfig.ads?.setImmersiveMode(true)
//                Log.e("AdsLoader", "onAdLoaded")
//
//                adConfig.ads?.fullScreenContentCallback =
//                    object : FullScreenContentCallback() {
//                        override fun onAdDismissedFullScreenContent() {
//                            super.onAdDismissedFullScreenContent()
//                            Log.e("AdsLoader", "onAdDismissedFullScreenContent")
//                            adConfig.onAdDismissed.invoke(null)
//                        }
//
//                        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
//                            super.onAdFailedToShowFullScreenContent(p0)
//                            adConfig.onAdDismissed.invoke(p0)
//                        }
//
//                        override fun onAdShowedFullScreenContent() {
//                            super.onAdShowedFullScreenContent()
//                            Log.e("AdsLoader", "onAdDismissedFullScreenContent")
//                            adConfig.onAdDisplayed.invoke()
//                        }
//                    }
//
//                adConfig.ads?.show(context as Activity)
//            }
//
//            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
//                Log.e("AdsLoader", "${loadAdError.message}")
//            }
//        })
//}