package ru.keepitlock.featurefavorites.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.keepitlock.featurefavorites.domain.model.Course

interface FavoritesRepository {

    fun getFavorites(): Flow<List<Course>>

    suspend fun removeFavorite(courseId: Int)
}