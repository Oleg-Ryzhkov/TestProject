package com.example.testapplication


import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    var success = false
    fun parseJSON() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://efs5i1ube5.execute-api.eu-central-1.amazonaws.com/prod/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(APIService::class.java)
        CoroutineScope(Dispatchers.IO).launch {

            val response = service.getEmployees()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    val items = response.body()
                    if (items != null) {
                        simpleJSONModel.link = items.link
                        simpleJSONModel.home = items.home
                        success = true
                    }

                } else {
                    success = false
                }
            }
        }
    }
}