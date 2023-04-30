package com.cashcontrol.feature.extenstion

import androidx.lifecycle.LifecycleOwner
import com.cashcontrol.feature.domain.DisposableObserver
import kotlinx.coroutines.flow.Flow

inline fun <reified T> Flow<T>.observeOnLifecycle(
    lifecycleOwner: LifecycleOwner,
    noinline collector: suspend (T) -> Unit
) {
    DisposableObserver(lifecycleOwner, this, collector)
}
