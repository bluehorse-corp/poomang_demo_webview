package com.example.androidwebpp.webclient

import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import com.example.androidwebpp.AdverDialog
import com.example.androidwebpp.MainActivity
import org.json.JSONException
import org.json.JSONObject

class WebViewChromeClient() : WebChromeClient() {

    companion object {
        private const val LOG_TAG = "WebViewChromeClient"
    }

    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        try {
           val jsonData =  JSONObject(consoleMessage?.message()!!)
            if (jsonData.has("event")) {
                when (jsonData.get("event")) {
                    "answer" -> {
                        // 질문에 대한 답변
                        Log.e(LOG_TAG, "답 : ${jsonData.get("data")}")
                    }
                    "load_result"->  {
                        //로딩 호출 시 이벤트
                        Log.e(LOG_TAG, "로딩시작")

                        AdverDialog(  MainActivity.instance ,
                            {
                                fun onClose() {
                                    Log.e("111","로딩시작 광고");
                                }
                            })
                            .show()
                    }
                    "process_scene"-> {
                        // 현재 구조상 이전신과 현재씬 두번이 호출되는데 마지막이 현재의 위치가 된다.
                        Log.e(LOG_TAG, "씬: : ${jsonData.get("data")}")
                        var processJson = JSONObject(jsonData.get("data").toString())
                        MainActivity.instance!!.getMainActivity().setProcessText(
                            "씬이름: " + processJson.get("scene").toString()
                        )
                        MainActivity.instance!!.getMainActivity().setTotalText(
                            "총 질문수" + processJson.get("total").toString()
                        )

                    }
                    "completeGame" -> {
                        Log.e(LOG_TAG, "종료")
                    }
                    else -> print("default")
                }
            }
        } catch (e: JSONException) {
            return false;
        }
        return true
    }
}
