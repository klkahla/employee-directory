package com.example.employeedirectory

import com.example.employeedirectory.data.DefaultEmployeeRepository
import com.example.employeedirectory.fake.FakeApiService
import com.example.employeedirectory.fake.FakeDataSource
import com.example.employeedirectory.model.Employee
import com.example.employeedirectory.model.EmployeeList
import com.example.employeedirectory.network.ApiService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

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

    @Test
    fun employeeRepository_getEmployees_emptyList() = runTest {
        val emptyApiService = object : ApiService {
            override suspend fun getEmployees() = EmployeeList(emptyList())
        }
        val repository = DefaultEmployeeRepository(apiService = emptyApiService)
        assertEquals(emptyList<Employee>(), repository.getEmployees())
    }

    @Test
    fun employeeRepository_getEmployees_verifyExceptionHandling() = runTest {
        val apiService = mock(ApiService::class.java)
        `when`(apiService.getEmployees()).thenThrow(RuntimeException("Test exception"))

        val repository = DefaultEmployeeRepository(apiService = apiService)
        try {
            repository.getEmployees()
        } catch (e: Exception) {
            assertEquals("Test exception", e.message)
        }
    }
}