package com.ctapk.poly.features.sarch.viewmodel

import com.ctapk.poly.model.Model3d

sealed class Model3dListViewState {

    data class Model3dLoaded(val modele3dList: ArrayList<Model3d>) : Model3dListViewState()
    data class FailedToLoad(val exception: Throwable) : Model3dListViewState()
}