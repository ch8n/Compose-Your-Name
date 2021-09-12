package io.github.ch8n.whatis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.ch8n.whatis.ui.navigation.WhatisNavigation
import io.github.ch8n.whatis.ui.theme.WhatisTheme

//
//data class AdConfig(
//    var ads: InterstitialAd? = null,
//    var onAdDisplayed: () -> Unit = {},
//    var onAdDismissed: (error: AdError?) -> Unit = {},
//    var onAdError: (LoadAdError) -> Unit = {},
//)

class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatisTheme {
                Surface(color = MaterialTheme.colors.background) {
                    WhatisNavigation()
                }
            }
        }
    }


}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WhatisTheme {
        Greeting("Android")
    }
}