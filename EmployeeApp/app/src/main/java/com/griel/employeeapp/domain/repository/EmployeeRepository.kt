package com.griel.employeeapp.domain.repository

import com.griel.employeeapp.data.model.Employee

interface EmployeeRepository {
    fun doNetworkCall()
    fun getEmployees(): List<Employee>
}