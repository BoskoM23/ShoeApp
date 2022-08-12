package com.bendingbytes.shoes.domain

sealed class DataState<out R> {
    object Loading : DataState<Nothing>()
    data class Success<out R>(val data: R) : DataState<R>()
    data class Error(val throwable: Throwable) : DataState<Nothing>()
}
