package com.example.employeedirectory.data

import androidx.room.RoomDatabase
import com.example.employeedirectory.data.Employee as EmployeeDB
import com.example.employeedirectory.model.Employee
import com.example.employeedirectory.network.ApiService

interface EmployeeRepository {
    suspend fun getEmployeesFromNetwork() : List<Employee>
    suspend fun getEmployeesFromDatabase() : List<EmployeeDB>
    fun saveEmployees(employees: List<Employee>)
}

class DefaultEmployeeRepository(
    private val apiService: ApiService,
    private val db: AppDatabase
) : EmployeeRepository {
    override suspend fun getEmployeesFromNetwork(): List<Employee> {
        return apiService.getEmployees().employees
    }

    override suspend fun getEmployeesFromDatabase(): List<EmployeeDB> {
        return db.employeeDao().getAll()
    }

    override fun saveEmployees(employees: List<Employee>) {
        db.employeeDao().insertAll(
            employees.map { it.toEmployeeDE() }
        )
    }
}

fun Employee.toEmployeeDE(): EmployeeDB = EmployeeDB(
    uid = 1,
    fullName = fullName,
    phoneNumber = phoneNumber,
    emailAddress = emailAddress,
    photoURLSm = photoURLSm,
    team = team
)