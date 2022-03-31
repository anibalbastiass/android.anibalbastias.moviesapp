package com.backbase.assignment.feature.data.remote.state

sealed class APIState<out T : Any> {
    object Loading : APIState<Nothing>()
    data class Success<out T : Any>(val data: T) : APIState<T>()
    data class Empty(val error: String) : APIState<Nothing>()
    data class Error(val error: String) : APIState<Nothing>()
}