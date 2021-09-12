package io.github.ch8n.whatis.ui.screens.shareName

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

data class NameState(
    val firstName: String,
    val lastName: String,
    val firstRandomIndex: Int,
    val secondRandomIndex: Int,
)

@Composable
fun NameToBitmap(
    nameState: State<NameState>
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val _firstName = nameState.value.firstName
        val _lastName = nameState.value.lastName
        val _firstRandomIndex = nameState.value.firstRandomIndex
        val _secondRandomIndex = nameState.value.secondRandomIndex

        Text(
            text = "If",
            style = MaterialTheme.typography.h2,
            fontSize = 42.sp,
            fontWeight = FontWeight.Medium
        )

        if (_firstName.length >= 7 || _lastName.length >= 7) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    _firstName.forEachIndexed { index, _char ->
                        Text(
                            text = if (index == 0) _char.uppercaseChar()
                                .toString() else _char.toString(),
                            style = MaterialTheme.typography.h1,
                            fontSize = 56.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = if (index == _firstRandomIndex || index == _firstRandomIndex + 1) {
                                Modifier.border(width = 1.dp, color = Color.Red)
                            } else {
                                Modifier
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    _lastName.forEachIndexed { index, _char ->
                        Text(
                            text = _char.toString(),
                            style = MaterialTheme.typography.h1,
                            fontSize = 56.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = if (index == _secondRandomIndex || index == _secondRandomIndex + 1) {
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
                _firstName.forEachIndexed { index, _char ->
                    Text(
                        text = _char.toString(),
                        style = MaterialTheme.typography.h1,
                        fontSize = 56.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = if (index == _firstRandomIndex || index == _firstRandomIndex + 1) {
                            Modifier.border(width = 1.dp, color = Color.Red)
                        } else {
                            Modifier
                        }
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                _lastName.forEachIndexed { index, _char ->
                    Text(
                        text = _char.toString(),
                        style = MaterialTheme.typography.h1,
                        fontSize = 56.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = if (index == _secondRandomIndex || index == _secondRandomIndex + 1) {
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
            _firstName.getOrNull(_firstRandomIndex)
        }${
            _firstName.getOrNull(_firstRandomIndex + 1)
        }${
            _lastName.getOrNull(_secondRandomIndex)
        }${
            _lastName.getOrNull(_secondRandomIndex + 1)
        }".lowercase(Locale.getDefault()).run {
            take(1).uppercase(Locale.getDefault()) + drop(1)
        }

        Text(
            text = name,
            style = MaterialTheme.typography.h1,
            fontSize = 56.sp,
            fontWeight = FontWeight.SemiBold,
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "What's Yours",
            style = MaterialTheme.typography.h2,
            fontSize = 36.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "Name?",
            style = MaterialTheme.typography.h2,
            fontSize = 36.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}


fun View.toBitmap(): Bitmap? {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    draw(canvas)
    return bitmap
}

suspend fun saveImage(image: Bitmap, context: Context): Uri? =
    withContext(Dispatchers.IO) {
        val imagesFolder = File(context.cacheDir, "images")
        var uri: Uri? = null
        try {
            imagesFolder.mkdirs()
            val file = File(imagesFolder, "shared_image.png")
            val stream = FileOutputStream(file)
            image.compress(Bitmap.CompressFormat.PNG, 90, stream)
            stream.flush()
            stream.close()
            uri = FileProvider.getUriForFile(context, "com.mydomain.fileprovider", file)
        } catch (e: IOException) {
            Log.d("Error", "IOException while trying to write file for sharing: " + e.message)
        }
        uri
    }


fun shareImageUri(context: Context, uri: Uri) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.putExtra(Intent.EXTRA_STREAM, uri)
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    intent.type = "image/png"
    context.startActivity(intent)
}