package ru.keepitlock.featurefavorites.di

import android.content.Context
import ru.keepitlock.coredata.model.dao.FavoritesDao

interface FavoritesDependencies {

    fun context(): Context

    fun favoritesDao(): FavoritesDao
}