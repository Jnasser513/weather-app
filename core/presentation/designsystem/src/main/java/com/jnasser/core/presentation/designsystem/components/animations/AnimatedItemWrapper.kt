package com.jnasser.core.presentation.designsystem.components.animations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

@Composable
fun AnimatedItemWrapper(
    index: Int,
    onAnimationEnd: () -> Unit,
    content: @Composable () -> Unit
) {
    LaunchedEffect(index) {
        delay(1000)
        onAnimationEnd()
    }

    content()
}