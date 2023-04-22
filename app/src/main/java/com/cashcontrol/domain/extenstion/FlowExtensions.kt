package com.cashcontrol.domain.extenstion

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

inline fun <T> Flow<T>.observe(
    scope: CoroutineScope,
    crossinline action: (value: T) -> Unit
): Job {
    return onEach { value ->
        action(value)
    }.launchIn(scope)
}
