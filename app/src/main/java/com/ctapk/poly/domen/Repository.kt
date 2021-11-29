package com.ctapk.poly.domen

import com.ctapk.poly.model.Result
import com.ctapk.poly.model.Model3d

interface Repository {

    suspend fun loadModel3dList(query:String): Result<ArrayList<Model3d>>
//    suspend fun loadModler3d(model3d.uid: Int): Result<MovieDetails>
}
