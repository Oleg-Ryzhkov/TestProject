package com.example.testapplication

import com.google.gson.annotations.SerializedName

data class SimpleJSONModel(

    @SerializedName("link")
    var link: String?,

    @SerializedName("home")
    var home: String?,
)
