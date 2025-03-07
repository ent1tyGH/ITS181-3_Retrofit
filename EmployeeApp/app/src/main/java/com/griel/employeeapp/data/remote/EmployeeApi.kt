package com.griel.employeeapp.data.remote

import com.griel.employeeapp.data.model.Employee
import retrofit2.http.GET

interface EmployeeApi {
    @GET("/api/employee")
    suspend fun getEmployees(): List<Employee>
}