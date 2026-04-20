package com.jnasser.core.presentation.designsystem.components.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun AnimatedContent(
    visible: Boolean,
    enterAnim: EnterTransition = fadeIn(animationSpec = tween(WeatherMotionTokens.Long)),
    exitAnim: ExitTransition = fadeOut(animationSpec = tween(WeatherMotionTokens.Medium)),
    onShown: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val visibilityState = remember { MutableTransitionState(false) }
    visibilityState.targetState = visible

    LaunchedEffect(visibilityState.isIdle, visibilityState.currentState, visible) {
        if (visible && visibilityState.isIdle && visibilityState.currentState) {
            onShown()
        }
    }

    AnimatedVisibility(
        visibleState = visibilityState,
        enter = enterAnim,
        exit = exitAnim
    ) {
        content()
    }
}
