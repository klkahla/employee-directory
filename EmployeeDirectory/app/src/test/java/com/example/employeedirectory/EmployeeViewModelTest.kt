package com.example.employeedirectory

import com.example.employeedirectory.fake.FakeDataSource
import com.example.employeedirectory.fake.FakeEmployeeRepository
import com.example.employeedirectory.rules.TestDispatcherRule
import com.example.employeedirectory.ui.screens.EmployeeUIState
import com.example.employeedirectory.ui.screens.EmployeeViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

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

}