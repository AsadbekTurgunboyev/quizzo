package com.example.quizzo.domain.repository

import com.example.quizzo.data.models.MainResponse
import io.reactivex.Observable
import com.example.quizzo.data.models.register.RegisterRequest
import com.example.quizzo.data.models.register.RegisterResponse

interface RegisterRepository {

    fun register(registerRequest: RegisterRequest): Observable<MainResponse<RegisterResponse>>

}