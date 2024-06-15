package com.dojah_inc.dojah_android_sdk.core

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()

    object Loading: Result<Nothing>()

    sealed class Error : Result<Nothing>() {
        object NetworkError : Error()

        object TimeoutError : Error()

        data class NoDataError(val code:Int?=null) : Error()
        /**
         * Error gotten from the response body
         * It sends the entire result unlike other errors so the receiver will know exactly
         * how to handle it
         * It is very similar to Success, but the fact that it is an error completely changes its usage
         * */
        data class ApiError(val error: Map<Any, Any>?,val code:Int?=null) : Error()
    }
}