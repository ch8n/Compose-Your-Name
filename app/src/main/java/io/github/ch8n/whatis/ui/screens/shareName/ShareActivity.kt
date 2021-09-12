package io.github.ch8n.whatis.ui.screens.shareName

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import io.github.ch8n.whatis.ui.theme.WhatisTheme
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import whatis.R

class ShareActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)


        val firstName = requireNotNull(intent.extras?.getString("firstName"))
        val lastName = requireNotNull(intent.extras?.getString("lastName"))
        val firstRandomIndex = requireNotNull(intent.extras?.getInt("firstIndex"))
        val secondRandomIndex = requireNotNull(intent.extras?.getInt("secondIndex"))

        val composeView = findViewById<ComposeView>(R.id.compose_view)

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

        findViewById<Button>(R.id.button).setOnClickListener {
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
}