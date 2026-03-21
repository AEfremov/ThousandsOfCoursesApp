package ru.keepitlock.featurefavorites.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.keepitlock.coredata.model.dao.FavoritesDao
import ru.keepitlock.featurefavorites.domain.model.Course
import ru.keepitlock.featurefavorites.domain.model.toDomain
import ru.keepitlock.featurefavorites.domain.repository.FavoritesRepository
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDao: FavoritesDao
) : FavoritesRepository {

    override fun getFavorites(): Flow<List<Course>> {
        return favoritesDao.getAllFavorites().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun removeFavorite(courseId: Int) {
        favoritesDao.deleteFavoriteById(courseId)
    }
}