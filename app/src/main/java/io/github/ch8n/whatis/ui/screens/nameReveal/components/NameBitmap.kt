package io.github.ch8n.whatis.ui.screens.nameReveal.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*


class NameBitmapView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayoutCompat(context, attrs, defStyle) {

    fun setup(
        firstName: String,
        lastName: String,
        firstRandomIndex: Int,
        secondRandomIndex: Int,
        onBitmapCreated: (bitmap: Bitmap) -> Unit
    ) {
        val width = 600
        val height = 670

        val view = ComposeView(context)
        view.visibility = View.GONE
        view.layoutParams = LayoutParams(width, height)
        this.addView(view)

        view.setContent {
            NameToBitmap(firstName, lastName, firstRandomIndex, secondRandomIndex)
        }

        viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val graphicUtils = GraphicUtils()
                val bitmap = graphicUtils
                    .createBitmapFromView(view = view, width = width, height = height)
                onBitmapCreated(bitmap)
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

}

class GraphicUtils {

    fun createBitmapFromView(view: View, width: Int, height: Int): Bitmap {
        view.layoutParams = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )

        view.measure(
            View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        )

        view.layout(0, 0, width, height)

        val canvas = Canvas()
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        canvas.setBitmap(bitmap)
        view.draw(canvas)

        return bitmap
    }
}

@Composable
fun NameToBitmap(
    firstName: String,
    lastName: String,
    firstRandomIndex: Int,
    secondRandomIndex: Int
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val _firstName = firstName
        val _lastName = lastName
        val _firstRandomIndex = firstRandomIndex
        val _secondRandomIndex = secondRandomIndex

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
            _firstName.get(_firstRandomIndex)
        }${
            _firstName.get(_firstRandomIndex + 1)
        }${
            _lastName.get(_secondRandomIndex)
        }${
            _lastName.get(_secondRandomIndex + 1)
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
    }
}