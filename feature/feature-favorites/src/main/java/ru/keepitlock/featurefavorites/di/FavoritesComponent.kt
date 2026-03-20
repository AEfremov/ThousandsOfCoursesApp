package ru.keepitlock.featurefavorites.di

import dagger.Component
import ru.keepitlock.coredi.scope.FeatureScope
import ru.keepitlock.featurefavorites.presentation.FavoritesFragment

@FeatureScope
@Component(
    dependencies = [FavoritesDependencies::class],
    modules = [FavoritesModule::class]
)
interface FavoritesComponent {

    fun inject(fragment: FavoritesFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: FavoritesDependencies): FavoritesComponent
    }
}