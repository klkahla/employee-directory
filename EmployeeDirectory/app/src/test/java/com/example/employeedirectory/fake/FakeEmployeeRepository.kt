package com.example.employeedirectory.fake

import com.example.employeedirectory.data.EmployeeRepository
import com.example.employeedirectory.model.Employee

class FakeEmployeeRepository : EmployeeRepository {
    override suspend fun getEmployees(): List<Employee> {
        return FakeDataSource.employeeList
    }
}