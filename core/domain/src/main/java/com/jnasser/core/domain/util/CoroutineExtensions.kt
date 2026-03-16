package com.jnasser.core.domain.util

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

suspend fun <T> withMinimumDuration(
    minDurationMillis: Long = 2000L,
    block: suspend () -> T
): T = coroutineScope {

    val resultDeferred = async { block() }
    val delayDeferred = async { delay(minDurationMillis) }

    val result = resultDeferred.await()
    delayDeferred.await()

    result
}