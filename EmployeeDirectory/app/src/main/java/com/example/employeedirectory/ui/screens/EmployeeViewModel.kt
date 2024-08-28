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
import com.example.employeedirectory.data.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

sealed interface EmployeeUIState {
    data class Success(val employeeList: List<Employee>) : EmployeeUIState
    data object Empty : EmployeeUIState
    data object Error : EmployeeUIState
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
                withContext(Dispatchers.IO) {
                    val employees = employeeRepository.getEmployeesFromDatabase()
                    if (employees.isEmpty()) {
                        EmployeeUIState.Empty
                    } else {
                        EmployeeUIState.Success(employees)
                    }
                }
            } catch (e: Exception) {
                println("exception $e")
                EmployeeUIState.Error
            }
            _isRefreshing.value = false
        }
    }

    fun getEmployeesFromNetwork() {
        viewModelScope.launch {
            val employees = employeeRepository.getEmployeesFromNetwork()
            withContext(Dispatchers.IO) {
                // Persist to the db
                employeeRepository.saveEmployees(employees)
            }
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