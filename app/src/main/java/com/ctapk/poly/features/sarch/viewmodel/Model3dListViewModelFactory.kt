package com.ctapk.poly.features.sarch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ctapk.poly.domen.Repository

class Model3dListViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        Model3dListViewModelImpl(repository) as T
}