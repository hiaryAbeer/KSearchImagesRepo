package com.abeerapps.ishowimagesapp.images.domain

sealed class Resources<T>(
    val data: T? = null,
    val exception: Exception? = null
) {
    class Success<T>( data: T) : Resources<T>(data)
    class Error<T>(data: T? = null, exception: Exception): Resources<T>(data, exception)
}