package com.example.testapplication

import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("https://efs5i1ube5.execute-api.eu-central-1.amazonaws.com/prod/")
    suspend fun getEmployees(): Response<SimpleJSONModel>
}