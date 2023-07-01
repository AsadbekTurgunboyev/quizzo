package com.example.quizzo.data.repositories

import com.example.quizzo.data.models.MainResponse
import com.example.quizzo.data.models.register.RegisterRequest

import io.reactivex.Observable
import com.example.quizzo.data.models.register.RegisterResponse
import com.example.quizzo.domain.repository.RegisterRepository
import com.example.quizzo.domain.source.ApiService

class RegisterRepositoryImp(private val apiService: ApiService) : RegisterRepository{

    override fun register(registerRequest: RegisterRequest): Observable<MainResponse<RegisterResponse>> {
        return apiService.register(registerRequest)
    }




}