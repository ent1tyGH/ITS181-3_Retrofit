package com.griel.employeeapp.di

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.annotations.SerializedName

enum class EmploymentType {
    @SerializedName("FULL_TIME") FULL_TIME,
    @SerializedName("PART_TIME") PART_TIME,
    @SerializedName("CONTRACTOR") CONTRACTOR,
    @SerializedName("INTERN") INTERN
}


object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}