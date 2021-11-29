package com.ctapk.poly.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.academy.fundamentals.homework.di.Model3dRepositoryProvider
import com.android.academy.fundamentals.homework.di.NetworkModule
import com.ctapk.poly.R
import com.ctapk.poly.data.remote.retrofit.RetrofitDataSource
import com.ctapk.poly.data.Model3dRepositoryImpl
import com.ctapk.poly.domen.Repository


class MainActivity : AppCompatActivity(), Model3dRepositoryProvider {

    private val networkModule = NetworkModule()
    private val remoteDataSource = RetrofitDataSource(networkModule.api)
    private val model3dRepository = Model3dRepositoryImpl(remoteDataSource)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun provideMovieRepository(): Repository = model3dRepository
}