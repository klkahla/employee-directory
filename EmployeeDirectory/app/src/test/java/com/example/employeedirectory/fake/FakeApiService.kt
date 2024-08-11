package com.example.employeedirectory.fake

import com.example.employeedirectory.model.EmployeeList
import com.example.employeedirectory.network.ApiService

class FakeApiService : ApiService {
    override suspend fun getEmployees(): EmployeeList {
        return EmployeeList(FakeDataSource.employeeList)
    }
}