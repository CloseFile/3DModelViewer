package com.ctapk.poly.features.sarch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ctapk.poly.domen.Repository
import com.ctapk.poly.extensions.exhaustive
import com.ctapk.poly.model.Failure
import com.ctapk.poly.model.Success
import com.ctapk.poly.model.Model3d
import com.ctapk.poly.model.Result

import kotlinx.coroutines.launch

class Model3dListViewModelImpl(private val repository: Repository) : Model3dListViewModel() {
    override val model3dListStateOutput = MutableLiveData<Model3dListViewState>()

    init {
        loadModel3dList("")
    }

    fun loadModel3dList(query: String) {
        viewModelScope.launch {
            handleResult(repository.loadModel3dList(query))
        }
    }

    private fun handleResult(result: Result<ArrayList<Model3d>>) {
        when (result) {
            is Success ->
                model3dListStateOutput.postValue(Model3dListViewState.Model3dLoaded(result.data))
            is Failure ->
                model3dListStateOutput.postValue(Model3dListViewState.FailedToLoad(result.exception))
        }.exhaustive
    }


}
