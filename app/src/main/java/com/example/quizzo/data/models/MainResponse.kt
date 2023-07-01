package com.example.quizzo.data.models

data class MainResponse<T>(

    val status: String,
    val code: Int,
    val message: String,
    val data: T
)
