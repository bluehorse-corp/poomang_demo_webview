package com.example.androidwebpp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidwebpp.webclient.AppWebViewClient
import com.example.androidwebpp.webclient.WebViewChromeClient

/**
 * Simple [WebView] based activity.
 *
 * Example code is taken from https://developer.android.com/develop/ui/views/layout/webapps/webview
 */
class MainActivity : AppCompatActivity() {
    private lateinit var myWebView: WebView
    private lateinit var txtProcess: TextView
    private lateinit var txtTotal: TextView

    companion object {
        var instance: MainActivity? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance = this
        setupWebView()
    }

    fun getMainActivity(): MainActivity {
        return instance as MainActivity
    }

    fun setProcessText(txt: String) {
        txtProcess = findViewById(R.id.progress_text)
        txtProcess.text = txt
    }

    fun setTotalText(txt: String) {
        txtTotal = findViewById(R.id.total_text)
        txtTotal.text = txt
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        myWebView = findViewById(R.id.webview)
        myWebView.loadUrl("https://tag.poomang.com/demo/index.html")
        myWebView.settings.javaScriptEnabled = true
        myWebView.webViewClient = AppWebViewClient()
        myWebView.webChromeClient = WebViewChromeClient()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
            myWebView.goBack()
            return true
        }
        // If it wasn't the Back key or there's no webpage history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }


}