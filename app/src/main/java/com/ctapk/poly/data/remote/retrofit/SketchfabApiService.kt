package com.ctapk.poly.data.remote.retrofit

import com.ctapk.poly.model.Model3dSearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SketchfabApiService {
    @GET("search")
    suspend fun search(
        @Query("type") models: String,
        @Query("downloadable") downloadable: Boolean,
        @Query("staffpicked") staffPicked: Boolean,
        @Query("q") query: String?,
    ): Response<Model3dSearchResult>

}