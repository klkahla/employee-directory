package com.example.employeedirectory.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employee(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "phone_number") val phoneNumber: String?,
    @ColumnInfo(name = "email_address") val emailAddress: String,
    @ColumnInfo(name = "photo_url_sm") val photoURLSm: String?,
    @ColumnInfo(name = "team") val team: String,
)
