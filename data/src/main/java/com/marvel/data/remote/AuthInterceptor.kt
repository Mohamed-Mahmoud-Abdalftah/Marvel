package com.marvel.data.remote


import com.marvel.core.BuildConfig.MARVEL_PRIVATE_KEY
import com.marvel.core.BuildConfig.MARVEL_PUBLIC_KEY
import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest
import javax.inject.Inject


class AuthInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val ts = System.currentTimeMillis().toString()
        val hash = generateMarvelHash(ts, MARVEL_PRIVATE_KEY, MARVEL_PUBLIC_KEY)

        val newUrl = chain.request().url.newBuilder()
            .addQueryParameter("ts", ts)
            .addQueryParameter("apikey", MARVEL_PUBLIC_KEY)
            .addQueryParameter("hash", hash)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }

    private fun generateMarvelHash(ts: String, privateKey: String, publicKey: String): String {
        val input = "$ts$privateKey$publicKey"
        return MessageDigest.getInstance("MD5")
            .digest(input.toByteArray())
            .joinToString("") { "%02x".format(it) }
    }
}

