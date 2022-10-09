package com.example.instagram_clone.sealed

sealed class DataResponse<T>(
    var data: T? = null,
    var error: com.example.instagram_clone.sealed.Error? = null,
) {
    class Success<T>(data: T) : DataResponse<T>(data = data)
    class Error<T>(error: com.example.instagram_clone.sealed.Error) : DataResponse<T>(error = error)
}