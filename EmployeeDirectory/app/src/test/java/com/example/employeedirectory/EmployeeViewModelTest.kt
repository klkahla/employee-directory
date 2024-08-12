package com.example.employeedirectory

import androidx.lifecycle.Observer
import com.example.employeedirectory.data.EmployeeRepository
import com.example.employeedirectory.fake.FakeDataSource
import com.example.employeedirectory.fake.FakeEmployeeRepository
import com.example.employeedirectory.rules.TestDispatcherRule
import com.example.employeedirectory.ui.screens.EmployeeUIState
import com.example.employeedirectory.ui.screens.EmployeeViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.io.IOException

class EmployeeViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun employeeViewModel_getEmployees_verifyEmployeeUiStateSuccess() =
        runTest {
            val employeeViewModel = EmployeeViewModel(
                employeeRepository = FakeEmployeeRepository()
            )
            assertEquals(
                EmployeeUIState.Success(FakeDataSource.employeeList),
                employeeViewModel.employeeUIState
            )
        }

    @Test
    fun `getEmployees should update employeeUIState with error`() = runTest {
        val employeeRepository = mock(EmployeeRepository::class.java)
        `when`(employeeRepository.getEmployees()).thenThrow(RuntimeException("Test exception"))

        val employeeViewModel = EmployeeViewModel(employeeRepository)
        employeeViewModel.getEmployees()
        assertEquals(EmployeeUIState.Error, employeeViewModel.employeeUIState)
    }
}