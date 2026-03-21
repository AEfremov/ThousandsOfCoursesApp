package ru.keepitlock.featurefavorites.domain.uescase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.keepitlock.featurefavorites.domain.repository.FavoritesRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(courseId: Int) = withContext(Dispatchers.IO) {
        repository.removeFavorite(courseId)
    }
}