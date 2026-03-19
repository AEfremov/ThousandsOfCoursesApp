package ru.keepitlock.featurecourses.di

import android.content.Context
import retrofit2.Retrofit

interface CoursesDependencies {
    fun context(): Context
    fun retrofit(): Retrofit
}