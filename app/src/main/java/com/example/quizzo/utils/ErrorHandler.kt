package com.example.quizzo.utils

import com.example.quizzo.data.models.MainResponse
import com.google.gson.Gson
import retrofit2.HttpException

object ErrorHandler {
    fun handle(e: Throwable): String {
        return if (e is HttpException) ({
            try {
                val errorBody = e.response()?.errorBody()?.string()
                val mainResponse = Gson().fromJson(errorBody, MainResponse::class.java)
                mainResponse.code
            } catch (e: Exception) {
                "An error occurred"
            }
        }) as String else {
            "An error occurred"
        }
    }
}