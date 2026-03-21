package ru.keepitlock.thousandsofcoursesapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import ru.keepitlock.coredata.model.dao.FavoritesDao
import ru.keepitlock.coredata.model.di.DataModule
import ru.keepitlock.coreremote.di.NetworkModule
import ru.keepitlock.featureaccount.di.AccountDependencies
import ru.keepitlock.featureauth.di.AuthDependencies
import ru.keepitlock.featureauth.presentation.AuthNavigator
import ru.keepitlock.featurecourses.di.CoursesDependencies
import ru.keepitlock.featurefavorites.di.FavoritesDependencies
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DataModule::class,
    ]
)
interface AppComponent :
    AuthDependencies,
    CoursesDependencies,
    FavoritesDependencies,
    AccountDependencies
{

    override fun context(): Context

    override fun retrofit(): Retrofit

    override fun getAuthNavigator(): AuthNavigator

    override fun favoritesDao(): FavoritesDao

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
