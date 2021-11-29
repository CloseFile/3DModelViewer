package com.ctapk.poly.data.remote.retrofit

import com.ctapk.poly.data.remote.RemoteDataSource
import com.ctapk.poly.model.Model3d


internal class RetrofitDataSource(
    private val api: SketchfabApiService,
) : RemoteDataSource {

    override suspend fun loadModel3dList(query:String): ArrayList<Model3d> {
        // TODO пагинация
        val responseServer = api.search("models", true, false, query)
        return responseServer.body()!!.results
    }
}