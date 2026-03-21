package ru.keepitlock.featurefavorites.domain.uescase

import ru.keepitlock.featurefavorites.domain.repository.FavoritesRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
}