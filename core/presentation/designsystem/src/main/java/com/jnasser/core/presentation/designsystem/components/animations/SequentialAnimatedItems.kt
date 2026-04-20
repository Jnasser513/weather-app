@file:OptIn(ExperimentalFoundationApi::class)

package com.jnasser.core.presentation.designsystem.components.animations

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SequentialAnimatedItems(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(20.dp),
    itemSpacing: Dp = 0.dp,
    itemEnterDurationMillis: Int = WeatherMotionTokens.Long,
    itemStaggerMillis: Int = WeatherMotionTokens.Stagger,
    itemOffsetPx: Int = 24,
    items: List<@Composable (hasAnimated: Boolean) -> Unit>,
    onSequenceEnd: () -> Unit
) {
    val motion = rememberWeatherMotionSettings()
    val enterDuration = motion.durationMillis(itemEnterDurationMillis)
    val resolvedItemOffsetPx = if (motion.animationsEnabled) itemOffsetPx else 0

    var visibleCount by rememberSaveable(items.size) { mutableIntStateOf(0) }
    var animatedIndexes by rememberSaveable(items.size) { mutableStateOf(emptySet<Int>()) }
    var hasCompletedSequence by rememberSaveable(items.size) { mutableStateOf(false) }

    LaunchedEffect(items.size, motion.durationScale) {
        if (items.isEmpty()) {
            if (!hasCompletedSequence) {
                hasCompletedSequence = true
                onSequenceEnd()
            }
            return@LaunchedEffect
        }

        visibleCount = 0
        animatedIndexes = emptySet()
        hasCompletedSequence = false

        if (!motion.animationsEnabled) {
            visibleCount = items.size
            animatedIndexes = items.indices.toSet()
            hasCompletedSequence = true
            onSequenceEnd()
            return@LaunchedEffect
        }

        items.indices.forEach { index ->
            visibleCount = index + 1
            if (index != items.lastIndex) {
                delay(motion.staggerMillis(itemStaggerMillis))
            }
        }
    }

    CompositionLocalProvider(
        LocalOverscrollFactory provides null
    ) {
        LazyColumn(
            modifier = modifier,
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(itemSpacing)
        ) {
            itemsIndexed(
                items = items,
                key = { index, _ -> index }
            ) { index, itemContent ->
                if (index < visibleCount) {
                    AnimatedContent(
                        visible = true,
                        enterAnim = fadeIn(
                            animationSpec = tween(
                                durationMillis = enterDuration,
                                easing = FastOutSlowInEasing
                            )
                        ) +
                            slideInVertically(
                                initialOffsetY = { resolvedItemOffsetPx },
                                animationSpec = tween(
                                    durationMillis = enterDuration,
                                    easing = FastOutSlowInEasing
                                )
                            ),
                        exitAnim = ExitTransition.None,
                        onShown = {
                            val nextAnimatedIndexes =
                                if (index in animatedIndexes) animatedIndexes
                                else animatedIndexes + index

                            if (nextAnimatedIndexes !== animatedIndexes) {
                                animatedIndexes = nextAnimatedIndexes
                            }

                            if (!hasCompletedSequence && nextAnimatedIndexes.size == items.size) {
                                hasCompletedSequence = true
                                onSequenceEnd()
                            }
                        }
                    ) {
                        itemContent(index in animatedIndexes)
                    }
                }
            }
        }
    }
}
