package com.relsellglobal.rxjavademokotlin.network

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class MyHttpRequests {
    companion object {

        fun triggerGetRequest() : Observable<String> {

            return Observable.create(object : ObservableOnSubscribe<String>{
                override fun subscribe(emitter: ObservableEmitter<String>) {
                    try {
                        val s = getResponseFromGetNetworkRequest()
                        emitter.onNext(s)
                        emitter.onComplete()
                    } catch (e:Exception) {
                        emitter.onError(e)
                    }
                }
            })
        }


        fun getResponseFromGetNetworkRequest() : String? {
            var res: String?
            val url = URL("http://www.android.com/")
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            try {
                val `in`: InputStream = BufferedInputStream(urlConnection.getInputStream())
                res = readStream(`in`)

            } finally {
                urlConnection.disconnect()
            }
            return res
        }

        fun readStream(inputStream: InputStream) : String {
            val isReader = InputStreamReader(inputStream)
            val reader = BufferedReader(isReader)
            val sb = StringBuffer()
            var str: String?
            while (reader.readLine().also { str = it } != null) {
                sb.append(str)
            }
            return sb.toString()
        }

    }
}