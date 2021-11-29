package com.android.academy.fundamentals.homework.di

import com.ctapk.poly.domen.Repository


internal interface Model3dRepositoryProvider {
    fun provideMovieRepository(): Repository
}
