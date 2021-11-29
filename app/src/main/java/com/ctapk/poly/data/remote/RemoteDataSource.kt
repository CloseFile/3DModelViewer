package com.ctapk.poly.data.remote

import com.ctapk.poly.model.Model3d


interface RemoteDataSource {

    suspend fun loadModel3dList(query:String): ArrayList<Model3d>
//    suspend fun loadMovie(movieId: Int): MovieDetails
}