package ru.keepitlock.coreremote.interceptor

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import javax.inject.Inject

class AssetInterceptor @Inject constructor(
    private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()
        if (url.contains("courses")) {
            return try {
                val json = context.assets.open("courses.json")
                    .bufferedReader()
                    .use { it.readText() }
                Response.Builder()
                    .code(200)
                    .message("OK")
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .body(json.toResponseBody("application/json".toMediaType()))
                    .build()
            } catch (e: IOException) {
                Response.Builder()
                    .code(500)
                    .message("Error reading asset file")
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .body("".toResponseBody("application/json".toMediaType()))
                    .build()
            }
        }
        return chain.proceed(request)
    }
}
