package io.github.ch8n.whatis.ui.screens.shareName

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.ch8n.whatis.ui.theme.WhatisTheme
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import whatis.R


data class ShareItem(
    val icon: Int,
    val name: String
)

val shareOptions = listOf(
    ShareItem(
        icon = R.drawable.share,
        name = "Share"
    ),
//    ShareItem(
//        icon = R.drawable.story,
//        name = "Stories"
//    ),
)

class ShareActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        val firstName = requireNotNull(intent.extras?.getString("firstName"))
        val lastName = requireNotNull(intent.extras?.getString("lastName"))
        val firstRandomIndex = requireNotNull(intent.extras?.getInt("firstIndex"))
        val secondRandomIndex = requireNotNull(intent.extras?.getInt("secondIndex"))

        val composeView = findViewById<ComposeView>(R.id.compose_view)
        val composeShareActions = findViewById<ComposeView>(R.id.compose_view_share_actions)

        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                WhatisTheme {
                    Surface(
                        color = MaterialTheme.colors.background,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        NameToBitmap(
                            mutableStateOf(
                                NameState(
                                    firstName, lastName, firstRandomIndex, secondRandomIndex
                                )
                            )
                        )
                    }
                }
            }
        }

        composeShareActions.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                WhatisTheme {
                    Surface(
                        color = MaterialTheme.colors.background,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Column {
                            val (isLoading, setLoading) = remember { mutableStateOf(false) }
                            Spacer(modifier = Modifier.height(20.dp))

                            if (isLoading) {
                                LinearProgressIndicator()
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                shareOptions.forEachIndexed { index, _share ->
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .padding(start = 16.dp)
                                            .clickable {
                                                setLoading(true)
                                                share(composeView)
                                                setLoading(false)
                                            }
                                    ) {
                                        Icon(
                                            painter = painterResource(id = _share.icon),
                                            contentDescription = "",
                                            tint = Color.Unspecified,
                                            modifier = Modifier.size(width = 36.dp, height = 36.dp)
                                        )
                                        Text(text = _share.name)
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            }
        }


    }

    fun share(composeView: ComposeView) {
        val bitmap = composeView.toBitmap()
        MainScope().launch {
            if (bitmap != null) {
                val uri = saveImage(bitmap, this@ShareActivity)
                if (uri != null) {
                    shareImageUri(this@ShareActivity, uri)
                } else {
                    Toast.makeText(
                        this@ShareActivity,
                        "uri is null",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@ShareActivity,
                    "bitmap null",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

