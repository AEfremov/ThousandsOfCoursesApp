package ru.keepitlock.featurefavorites.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.keepitlock.coredi.ViewModelKey
import ru.keepitlock.coredi.scope.FeatureScope
import ru.keepitlock.featurefavorites.presentation.FavoritesViewModel

@Module
abstract class FavoritesModule {

//    @Binds
//    @FeatureScope
//    abstract fun bindFavoritesRepository(impl: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel

//    companion object {
//        @Provides
//        @FeatureScope
//        fun provideFavoritesApi(retrofit: Retrofit): FavoritesApi {
//            return retrofit.create(FavoritesApi::class.java)
//        }
//    }
}