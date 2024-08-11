package com.example.employeedirectory.data

import com.example.employeedirectory.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val employeeRepository: EmployeeRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://s3.amazonaws.com/sq-mobile-interview/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val employeeRepository: EmployeeRepository by lazy {
        DefaultEmployeeRepository(retrofitService)
    }
}