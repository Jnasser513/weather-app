@file:OptIn(ExperimentalLayoutApi::class)

package com.jnasser.core.presentation.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.jnasser.core.presentation.designsystem.components.animations.AnimatedContent
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun AnimatedTypewriterText(
    modifier: Modifier = Modifier,
    text: String,
    delayPerLetter: Long = 30L,
    textStyle: TextStyle = LocalTextStyle.current,
    colorRange: List<IntRange>,
    primaryColor: Color = MaterialTheme.colorScheme.surface,
    defaultColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Row(modifier = modifier) {
        text.forEachIndexed { index, char ->
            var visible by remember { mutableStateOf(false) }

            var color = defaultColor

            for(range in colorRange) {
                if(index in range) {
                    color = primaryColor
                    break
                }
            }

            LaunchedEffect(Unit) {
                delay(index * delayPerLetter)
                visible = true
            }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 })
            ) {
                Text(
                    char.toString(),
                    style = textStyle.copy(color = color)
                )
            }
        }
    }
}

@Composable
fun AnimatedText(
    modifier: Modifier = Modifier,
    text: String,
    highlightWordPositions: List<Int> = emptyList(),
    highlightColor: Color = MaterialTheme.colorScheme.onSurface,
    defaultColor: Color = MaterialTheme.colorScheme.surface,
    textStyle: TextStyle = MaterialTheme.typography.headlineMedium,
    delayPerWord: Long = 100L
) {

    val exitAnimDelay = 500

    val wordsWithSpaces = remember(text) {
        // Regex para dividir y conservar los espacios
        Regex("""\S+\s*""").findAll(text).map { it.value }.toList()
    }

    val visibleStates = remember { mutableStateListOf<Boolean>() }

    LaunchedEffect(text) {
        delay(exitAnimDelay.milliseconds)
        visibleStates.clear()
        repeat(wordsWithSpaces.size) { visibleStates.add(false) }

        wordsWithSpaces.indices.forEach { index ->
            delay(delayPerWord)
            visibleStates[index] = true
        }
    }

    FlowRow(
        modifier = modifier
    ) {
        wordsWithSpaces.forEachIndexed { index, word ->
            val isHighlighted = index in highlightWordPositions
            val color = if (isHighlighted) highlightColor else defaultColor

            AnimatedContent(
                visible = visibleStates.getOrNull(index) == true,
                enterAnim = fadeIn(animationSpec = tween(500)) +
                        slideInVertically(initialOffsetY = { it / 2 }, animationSpec = tween(500)),
                exitAnim = fadeOut(animationSpec = tween(exitAnimDelay)),
                content = {
                    Text(
                        text = word,
                        style = textStyle.copy(color = color)
                    )
                }
            )
        }
    }
}