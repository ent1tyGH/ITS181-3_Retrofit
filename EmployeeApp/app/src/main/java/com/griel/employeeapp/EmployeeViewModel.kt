package com.griel.employeeapp

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.griel.employeeapp.data.model.Employee
import com.griel.employeeapp.data.remote.EmployeeApi
import com.griel.employeeapp.di.RetrofitClient
import kotlinx.coroutines.launch

class EmployeeViewModel: ViewModel() {
    private val _employees = MutableStateFlow<List<Employee>>(emptyList())
    val employees: StateFlow<List<Employee>> get() = _employees
    private val employeeApi = RetrofitClient.instance.create(EmployeeApi::class.java)

    init {
        fetchEmployees()
    }

    private fun fetchEmployees() {
        viewModelScope.launch {
            try {
                val response = employeeApi.getEmployees()
                _employees.value = response
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}