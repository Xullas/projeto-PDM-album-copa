package com.pdm.grupo2.albumfigurinhas.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grupo2.albumfigurinhas.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun WaitScreen(
    onLoadingComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        delay(1000.milliseconds)
        onLoadingComplete()
    }

    val infiniteTransition = rememberInfiniteTransition(label = "BallBounce")
    val bounceTranslationY by infiniteTransition.animateFloat(
        initialValue = -200f,
        targetValue = 500f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "VerticalMovement"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF6B1D2F)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bkg_espera),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
                        .scale(1.15f),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(id = R.drawable.bola),
            contentDescription = "Bola da Copa quicando",
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer {
                    translationY = bounceTranslationY
                }
        )
    }
}

@Preview(showBackground = true, widthDp = 412, heightDp = 917)
@Composable
fun WaitScreenPreview() {
    WaitScreen(onLoadingComplete = {})
}