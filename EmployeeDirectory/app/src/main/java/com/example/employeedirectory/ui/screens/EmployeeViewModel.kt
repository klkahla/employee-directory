package com.example.employeedirectory.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.employeedirectory.EmployeeApplication
import com.example.employeedirectory.data.EmployeeRepository
import com.example.employeedirectory.model.Employee
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface EmployeeUIState {
    data class Success(val employeeList: List<Employee>) : EmployeeUIState
    object Empty : EmployeeUIState
    object Error : EmployeeUIState
}

class EmployeeViewModel(private val employeeRepository: EmployeeRepository) : ViewModel() {
    var employeeUIState: EmployeeUIState by mutableStateOf(EmployeeUIState.Empty)
        private set

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        getEmployees()
    }

    fun getEmployees() {
        viewModelScope.launch {
            _isRefreshing.value = true
            employeeUIState = try {
                val employees = employeeRepository.getEmployees()
                if (employees.isEmpty()) {
                    EmployeeUIState.Empty
                } else {
                    EmployeeUIState.Success(employees)
                }
            } catch (e: IOException) {
                EmployeeUIState.Error
            } catch (e: HttpException) {
                EmployeeUIState.Error
            }
            _isRefreshing.value = false
        }
    }

    /**
     * Factory for [EmployeeViewModel] that takes [employeeRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as EmployeeApplication)
                val employeeRepository = application.container.employeeRepository
                EmployeeViewModel(employeeRepository = employeeRepository)
            }
        }
    }
}