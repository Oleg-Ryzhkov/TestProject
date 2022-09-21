package com.example.testapplication

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment


class WebWiewFragment : Fragment() {
    lateinit var simpleJSONModel: SimpleJSONModel
    private lateinit var myWebView: WebView
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_wiew, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        simpleJSONModel = (requireActivity() as MainActivity).simpleJSONModel
        sharedPreferences = requireActivity().getPreferences(MODE_PRIVATE)
        myWebView = view.findViewById(R.id.mywebwiew)

        if (savedInstanceState == null) {
            val savedLink = sharedPreferences.getString("saveurl", null)
            if (savedLink != null) {
                myWebView.loadUrl(simpleJSONModel.home.toString())

            } else if (myWebView.url == null) {
                myWebView.loadUrl(simpleJSONModel.link.toString())
            }
        } else {
            myWebView.restoreState(savedInstanceState)
        }
        myWebView.webViewClient = object : WebViewClient() {
            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                sharedPreferences.edit().putString("saveurl", myWebView.url).apply()
            }
        }
        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.allowContentAccess = true
        myWebView.settings.domStorageEnabled = true
        myWebView.settings.useWideViewPort = true

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(myWebView.canGoBack()){
                    myWebView.goBack()
                } else {
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        myWebView.saveState(outState)

    }

    companion object {
        @JvmStatic
        fun newInstance() = WebWiewFragment()
    }

}


