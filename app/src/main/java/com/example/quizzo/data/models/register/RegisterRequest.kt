package com.example.quizzo.data.models.register

data class RegisterRequest(
    val phone_number: String? = "",
    val username: String? = "",
    var password: String? = "",
    var password2: String? ="",
//    val city: String? = "",
//    val birthday: String? = ""
)
