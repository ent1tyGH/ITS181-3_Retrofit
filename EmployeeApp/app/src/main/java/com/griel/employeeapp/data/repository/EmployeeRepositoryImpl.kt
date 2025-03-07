package com.griel.employeeapp.data.repository
import android.app.Application
import com.griel.employeeapp.data.remote.EmployeeApi

class EmployeeRepositoryImpl constructor (
    private val api: EmployeeApi,
    private val appContext: Application
){
}