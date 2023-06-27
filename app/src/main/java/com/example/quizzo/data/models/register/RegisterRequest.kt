package com.example.quizzo.data.models.register

data class RegisterRequest(
    val phone_number: String,
    val username: String,
    val password: String,
    val password2: String
)
