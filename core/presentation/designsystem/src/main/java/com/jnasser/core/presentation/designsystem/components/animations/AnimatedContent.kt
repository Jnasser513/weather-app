package com.jnasser.core.presentation.designsystem.components.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable

@Composable
fun AnimatedContent(
    visible: Boolean,
    transition: EnterTransition = fadeIn(animationSpec = tween(1000)),
    content: @Composable () -> Unit,
    onAnimationEnd: () -> Unit = {}
) {
    AnimatedVisibility(
        visible = visible,
        enter = transition
    ) {
        content()
        onAnimationEnd()
    }
}