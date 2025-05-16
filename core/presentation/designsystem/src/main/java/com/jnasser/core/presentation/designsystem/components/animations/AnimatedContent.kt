package com.jnasser.core.presentation.designsystem.components.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable

@Composable
fun AnimatedContent(
    visible: Boolean,
    enterAnim: EnterTransition = fadeIn(animationSpec = tween(1000)),
    exitAnim: ExitTransition = fadeOut(animationSpec = tween(1000)),
    content: @Composable () -> Unit,
    onAnimationEnd: () -> Unit = {}
) {
    AnimatedVisibility(
        visible = visible,
        enter = enterAnim,
        exit = exitAnim
    ) {
        content()
        onAnimationEnd()
    }
}