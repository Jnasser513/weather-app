package com.jnasser.core.presentation.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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