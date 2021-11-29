package com.ctapk.poly.data

import com.ctapk.poly.data.remote.RemoteDataSource
import com.ctapk.poly.model.Result
import com.ctapk.poly.domen.Repository
import com.ctapk.poly.model.runCatchingResult
import com.ctapk.poly.model.Model3d


internal class Model3dRepositoryImpl(
    private val remoteDataResource: RemoteDataSource,
) : Repository {

    override suspend fun loadModel3dList(query:String): Result<ArrayList<Model3d>> {
        return runCatchingResult { remoteDataResource.loadModel3dList(query) }
    }

//    override suspend fun loadModler3d(movieId: Int): Result<MovieDetails> {
//        return runCatchingResult { remoteDataResource.loadMovie(movieId) }
//    }
}
