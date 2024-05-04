package com.gracodev.macromovies.utils

import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale

class LanguageInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val language = Locale.getDefault().language
        val newUrl = request.url.newBuilder()
            .addQueryParameter("language", language)
            .build()
        request = request.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(request)
    }
}