package com.example.testapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView

@SuppressLint("StaticFieldLeak")
val webWiewFragment = WebWiewFragment()
val fragmentLoading = FragmentLoading()
var simpleJSONModel = SimpleJSONModel(null, null)
val retrofit = Retrofit()

open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            retrofit.parseJSON()
            startfirstfragment()
            Handler(Looper.getMainLooper()).postDelayed({
                if (retrofit.success) {
                    starttwofragment()
                } else noConectToServer()
            }, 2000)
        }

    }

    @SuppressLint("SetTextI18n")
    fun noConectToServer() {
        val text = findViewById<TextView>(R.id.textView)
        text.text = "No connection to server"
    }

    fun startfirstfragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, fragmentLoading)
            commit()
        }
    }

    fun starttwofragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, webWiewFragment)
            commit()
        }
    }
}
