package com.example.mywallet.ui.components.animatedIcon

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mywallet.R
import com.example.mywallet.ui.dpToPx
import com.example.mywallet.ui.shimmerEffect
import com.example.mywallet.ui.waveAnimation
import java.lang.Math.sin


@Composable
fun GetWaveAnimation(completionPercentage: Float, color: Color) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val maxHeight = 100.dp.dpToPx()
    val waveValue by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 2000
                0.0f at 0 with LinearOutSlowInEasing
                1.0f at 1000
                0.0f at 2000 with LinearOutSlowInEasing
            }
        ), label = ""
    )
    Box(
        modifier = Modifier
            .height(90.dp)
            .padding(start = 3.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = CircleShape)
        ) {
            val waveAmplitude = 10f
            val waveFrequency = 1.5f
            for (x in 0 until size.width.toInt()) {
                val yOffset =
                    waveAmplitude * sin(waveFrequency * 2 * Math.PI * (x / size.width) + 2 * Math.PI * waveValue)
                val y = (1 - completionPercentage) * size.height + yOffset
                val limitedY = y.coerceIn(0.0, maxHeight.toDouble())
                drawLine(
                    color,
                    Offset(x.toFloat(), size.height),
                    Offset(x.toFloat(), limitedY.toFloat())
                )

            }
        }
    }
}

@Composable
fun AnimatedIcon(
    emptyIcon: Painter,
    fullIcon: Painter,
) {
    Box(
        modifier = Modifier.size(100.dp)
    ) {
        Image(
            painter = fullIcon,
            contentDescription = "Animated Icon",
            modifier = Modifier.size(100.dp).waveAnimation(Color.Red).drawWithContent {
                clipRect(top = size.height / 2f) {
                    this@drawWithContent.drawContent()
                }
            }
        )
//        GetWaveAnimation(completionPercentage = 0.5f, color = MaterialTheme.colorScheme.surfaceVariant)
        Image(
            painter = emptyIcon,
            contentDescription = "Animated Icon",
            modifier = Modifier.size(100.dp)
        )
    }
}

@Preview
@Composable
fun AnimatedIconPreview() {
    AnimatedIcon(
        emptyIcon = painterResource(id = R.drawable.blood),
        fullIcon = painterResource(id = R.drawable.blood_completed)
    )
}

// Water Animation
