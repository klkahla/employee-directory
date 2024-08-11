package com.example.employeedirectory.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmployeeList(
    val employees: List<Employee> = listOf()
)

@Serializable
data class Employee (
    /*
    {
      "uuid": "0d8fcc12-4d0c-425c-8355-390b312b909c",
      "full_name": "Justine Mason",
      "phone_number": "5553280123",
      "email_address": "jmason.demo@squareup.com",
      "biography": "Engineer on the Point of Sale team.",
      "photo_url_small": "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
      "photo_url_large": "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/large.jpg",
      "team": "Point of Sale",
      "employee_type": "FULL_TIME"
    }
     */
    val uuid: String = "",
    @SerialName("full_name")
    val fullName: String = "",
    @SerialName("phone_number")
    val phoneNumber: String = "",
    @SerialName("email_address")
    val emailAddress: String = "",
    val biography: String = "",
)