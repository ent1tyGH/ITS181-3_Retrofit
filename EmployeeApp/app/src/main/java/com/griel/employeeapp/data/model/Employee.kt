package com.griel.employeeapp.data.model

data class Employee(
    val id: Int,
    val name: String,
    val jobTitle: String,
    val salary: Double,
    val employmentType: EmploymentType
)
