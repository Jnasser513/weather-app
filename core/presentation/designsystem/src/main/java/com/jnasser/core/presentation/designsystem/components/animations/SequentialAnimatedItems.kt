@file:OptIn(ExperimentalFoundationApi::class)

package com.jnasser.core.presentation.designsystem.components.animations

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SequentialAnimatedItems(
    modifier: Modifier = Modifier,
    items: List<@Composable (hasAnimated: Boolean) -> Unit>,
    onSequenceEnd: () -> Unit
) {
    var currentIndex by rememberSaveable { mutableIntStateOf(0) }
    var animatedIndexes by rememberSaveable { mutableStateOf(setOf<Int>()) }

    CompositionLocalProvider(
        LocalOverscrollFactory provides null
    ) {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(20.dp)
        ) {
            itemsIndexed(
                items = items,
                key = { index, _ -> index }
            ) { index, itemContent ->

                if (index <= currentIndex) {
                    AnimatedItemWrapper(
                        index = index,
                        onAnimationEnd = {
                            animatedIndexes = animatedIndexes + index

                            if (index == currentIndex) {
                                currentIndex++
                                if (index == items.lastIndex) onSequenceEnd()
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