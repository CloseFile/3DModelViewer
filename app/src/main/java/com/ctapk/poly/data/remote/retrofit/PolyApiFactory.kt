package com.ctapk.poly.data.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PolyApiFactory {
    @JvmStatic
    fun createApi(): PolyApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://poly.googleapis.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(PolyApiService::class.java)
    }
}

object Constants {
    const val API_KEY_POLY = "AIzaSyDJw33d9XHvvFU7FV331DUf4lT9L5eCxRE"
    const val BASE_API_URL = "https://poly.googleapis.com"
}