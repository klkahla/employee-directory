package com.example.employeedirectory.fake

import com.example.employeedirectory.model.Employee

object FakeDataSource {
    const val uuidOne = "0d8fcc12-4d0c-425c-8355-390b312b909c"
    const val uuidTwo = "7d8fcc12-50cd-425c-1891-390b312b909c"

    const val fullNameOne = "Justine Mason"
    const val fullNameTwo = "Grace Hopper"

    const val phoneNumberOne = "555-230-9829"
    const val phoneNumberTwo = "208-483-2748"

    const val emailOne = "jmason.demo@squareup.com"
    const val emailTwo = "ghopper.demo@squareup.com"

    const val biographyOne = "Engineer on the Point of Sale team."
    const val biographyTwo = "Pioneer in computer science and Navy rear admiral."

    const val photoUrlSmallOne = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg"
    const val photoUrlSmallTwo = "https://s3.amazonaws.com/sq-mobile-interview/photos/5b48e8a1-2d71-4f1d-9b6e-d8f4e6ef7185/small.jpg"

    const val photoUrlLargeOne = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/large.jpg"
    const val photoUrlLargeTwo = "https://s3.amazonaws.com/sq-mobile-interview/photos/5b48e8a1-2d71-4f1d-9b6e-d8f4e6ef7185/large.jpg"

    const val teamOne = "Point of Sale"
    const val teamTwo = "Innovation and Technology"

    const val employeeTypeOne = "FULL_TIME"
    const val employeeTypeTwo = "PART_TIME"

    val employeeList = listOf(
        Employee(
            uuid = uuidOne,
            fullName = fullNameOne,
            phoneNumber = phoneNumberOne,
            emailAddress = emailOne,
            biography = biographyOne,
            photoURLSm = photoUrlSmallOne,
            photoURLLg = photoUrlLargeOne,
            team = teamOne,
            employeeType = employeeTypeOne
        ),
        Employee(
            uuid = uuidTwo,
            fullName = fullNameTwo,
            phoneNumber = phoneNumberTwo,
            emailAddress = emailTwo,
            biography = biographyTwo,
            photoURLSm = photoUrlSmallTwo,
            photoURLLg = photoUrlLargeTwo,
            team = teamTwo,
            employeeType = employeeTypeTwo
        )
    )

}