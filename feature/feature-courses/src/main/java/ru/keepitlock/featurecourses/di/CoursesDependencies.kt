package ru.keepitlock.featurecourses.di

import android.content.Context
import retrofit2.Retrofit
import ru.keepitlock.coredata.model.dao.FavoritesDao

interface CoursesDependencies {
    fun context(): Context
    fun retrofit(): Retrofit

    fun favoritesDao(): FavoritesDao
}