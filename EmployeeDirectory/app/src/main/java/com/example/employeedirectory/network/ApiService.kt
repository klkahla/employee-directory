package com.example.employeedirectory.network

import com.example.employeedirectory.model.Employee
import retrofit2.http.GET

interface ApiService {
    @GET("employees")
    suspend fun getEmployees(): List<Employee>
}