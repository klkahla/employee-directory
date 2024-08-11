package com.example.employeedirectory.data

import com.example.employeedirectory.model.Employee
import com.example.employeedirectory.network.ApiService

interface EmployeeRepository {
    suspend fun getEmployees() : List<Employee>
}

class DefaultEmployeeRepository(
    private val apiService: ApiService
) : EmployeeRepository {
    override suspend fun getEmployees(): List<Employee> {
        return apiService.getEmployees().employees
    }
}