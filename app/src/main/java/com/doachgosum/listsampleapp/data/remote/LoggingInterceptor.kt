package com.doachgosum.listsampleapp.data.remote

import com.doachgosum.listsampleapp.Logger
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        Logger.n(
            "[RESPONSE] URL: ${request.url().url()}" +
                    "\nHEADERS: ${request.headers()}" +
                    "\nBODY: ${request.body().toString()}"
        )

        val response = chain.proceed(request)
        Logger.n(
            "[RESPONSE] URL: ${response.request().url()}" +
                    "\nHEADERS: ${response.headers()}" +
                    "\nBODY: ${response.peekBody(Long.MAX_VALUE).string()}"
        )
        return response
    }
}