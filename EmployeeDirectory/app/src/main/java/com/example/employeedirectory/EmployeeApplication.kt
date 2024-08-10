package com.example.employeedirectory

import android.app.Application
import com.example.employeedirectory.data.AppContainer
import com.example.employeedirectory.data.DefaultAppContainer

class EmployeeApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}