package com.example.permissionapp






sealed class Sonuc<T>(
    val data: T? = null,
    val message: String? = null
) {
    data class Success<T>(val response: T) : Sonuc<T>(data = response)
    data class Error<T>(val errorMessage: String) : Sonuc<T>(message = errorMessage)
    object Loading : Sonuc<Nothing>()
}