package com.ctapk.poly.features.sarch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class Model3dListViewModel : ViewModel() {
    abstract val model3dListStateOutput: LiveData<Model3dListViewState>
}
