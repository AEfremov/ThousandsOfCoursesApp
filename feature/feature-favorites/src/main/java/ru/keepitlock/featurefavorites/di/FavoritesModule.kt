package ru.keepitlock.featurefavorites.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.keepitlock.coredi.ViewModelKey
import ru.keepitlock.coredi.scope.FeatureScope
import ru.keepitlock.featurefavorites.data.repository.FavoritesRepositoryImpl
import ru.keepitlock.featurefavorites.domain.repository.FavoritesRepository
import ru.keepitlock.featurefavorites.presentation.FavoritesViewModel

@Module
abstract class FavoritesModule {

    @Binds
    @FeatureScope
    abstract fun bindFavoritesRepository(impl: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel
}