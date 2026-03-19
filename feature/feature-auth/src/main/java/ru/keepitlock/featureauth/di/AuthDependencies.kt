package ru.keepitlock.featureauth.di

import android.content.Context
import ru.keepitlock.featureauth.presentation.AuthNavigator

interface AuthDependencies {
    fun context(): Context

    fun getAuthNavigator(): AuthNavigator
}
