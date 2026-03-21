package ru.keepitlock.featurefavorites.domain.uescase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.keepitlock.featurefavorites.domain.model.Course
import ru.keepitlock.featurefavorites.domain.repository.FavoritesRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    operator fun invoke(): Flow<List<Course>> {
        return repository.getFavorites().flowOn(Dispatchers.IO)
    }
}