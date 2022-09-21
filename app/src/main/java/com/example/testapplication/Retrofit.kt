package com.example.testapplication


import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class Retrofit {
    fun parseJSON(callback:(SimpleJSONModel)->Unit) {
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

                          val link = items.link.toString()
                          val home = items.home.toString()
                          callback(SimpleJSONModel(link, home))
                    }

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())
                     throw Exception()
                }
            }
        }

    }
}