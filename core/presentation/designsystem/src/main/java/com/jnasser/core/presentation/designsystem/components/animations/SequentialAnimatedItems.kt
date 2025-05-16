@file:OptIn(ExperimentalFoundationApi::class)

package com.jnasser.core.presentation.designsystem.components.animations

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SequentialAnimatedItems(
    modifier: Modifier = Modifier,
    items: List<@Composable () -> Unit>,
    onSequenceEnd: () -> Unit
) {
    var currentIndex by remember { mutableIntStateOf(0) }

    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(20.dp)
        ) {
            itemsIndexed(items) { index, itemContent ->
                if(index <= currentIndex) {
                    key(index) {
                        AnimatedItemWrapper(
                            index = index,
                            onAnimationEnd = {
                                if(index == currentIndex) {
                                    currentIndex++
                                    if(index == items.lastIndex) onSequenceEnd()
                                }
                            }
                        ) { itemContent() }
                    }
                }
            }
        }
    }
}