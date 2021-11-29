package com.android.academy.fundamentals.homework.di

import android.util.Log
import com.ctapk.poly.BuildConfig
import com.ctapk.poly.data.remote.retrofit.SketchfabApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {

//    private val baseUrl = "https://api.sketchfab.com/"
//    private val version = "v3/"
//
//    private val json = Json {
//        prettyPrint = true
//        ignoreUnknownKeys = true
//    }
//
//    private val contentType = "application/json".toMediaType()
//
//    private val loggingInterceptor = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }

    //    private val httpClient = OkHttpClient.Builder()
//        .connectTimeout(10, TimeUnit.SECONDS)
//        .writeTimeout(30, TimeUnit.SECONDS)
//        .readTimeout(10, TimeUnit.SECONDS)
//        .addNetworkInterceptor(loggingInterceptor)
//        .addInterceptor(ApiKeyInterceptor())
//        .build()
    private fun provideOkHttpClient(): OkHttpClient {
        val interceptor = Interceptor { chain ->
            var request = chain.request()
            val builder = request.newBuilder()

//            if (sToken != null) {
//                builder.addHeader(AUTH_TOKEN, sToken!!)
//            }

            request = builder.build()

            chain.proceed(request)
        }

        val httpInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.d(
                "NetworkModule", message)
        })
        httpInterceptor.level =
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(httpInterceptor)
            .build()
    }

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://api.sketchfab.com/v3/")
        .client(provideOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
//        .addConverterFactory(json.asConverterFactory(contentType))


    private val retrofit = retrofitBuilder.build()

    val api: SketchfabApiService by lazy { retrofit.create(SketchfabApiService::class.java) }
}

//class ApiKeyInterceptor : Interceptor {
//
//    companion object {
//        private const val API_KEY = "51ba76a60220d6833d3d6d4ac4368e53"
//    }
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val origin = chain.request()
//        val urlBuilder = origin.url.newBuilder()
//        val url = urlBuilder
//            .addQueryParameter("api_key", API_KEY)
//            .build()
//
//        val requestBuilder = origin.newBuilder()
//            .url(url)
//
//        val request = requestBuilder.build()
//        return chain.proceed(request)
//    }
//}
