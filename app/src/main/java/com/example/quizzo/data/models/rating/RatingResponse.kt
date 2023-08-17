package com.example.quizzo.data.models.rating

data class RatingResponse(
    val userName: String,
    val image: String,
    val rating_position: Int,
    val rating_px: Int
)