package com.example.employeedirectory

import com.example.employeedirectory.data.DefaultEmployeeRepository
import com.example.employeedirectory.fake.FakeApiService
import com.example.employeedirectory.fake.FakeDataSource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest

import org.junit.Test

class EmployeeRepositoryTest {
    @Test
    fun employeeRepository_geEmployees_verifyEmployeeList(){
        runTest {
            val repository = DefaultEmployeeRepository(
                apiService = FakeApiService()
            )
            assertEquals(FakeDataSource.employeeList, repository.getEmployees())
        }
    }
}