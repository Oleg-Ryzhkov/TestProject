package com.example.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    var simpleJSONModel = SimpleJSONModel(null, null)
    val fragmentLoading = FragmentLoading()
    val webWiewFragment = WebWiewFragment.newInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit()

        if (savedInstanceState == null) {
            retrofit.parseJSON { model ->
                simpleJSONModel = model
                startfirstfragment()
                Handler(Looper.getMainLooper()).postDelayed({
                    if (simpleJSONModel.link != null) {
                        starttwofragment()
                    }
                }, 1000)
            }
        }
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
