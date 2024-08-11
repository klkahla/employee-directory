package com.example.employeedirectory.network

import com.example.employeedirectory.model.Employee
import com.example.employeedirectory.model.EmployeeList
import retrofit2.http.GET

interface ApiService {
    @GET("employees.json")
    suspend fun getEmployees(): EmployeeList
}