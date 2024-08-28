package com.example.employeedirectory.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employee")
    fun getAll(): List<Employee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(employees: List<Employee>)
}