package ru.keepitlock.thousandsofcoursesapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import ru.keepitlock.coreremote.di.NetworkModule
import ru.keepitlock.featureauth.di.AuthDependencies
import ru.keepitlock.featureauth.presentation.AuthNavigator
import ru.keepitlock.featurecourses.di.CoursesDependencies
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AuthDependencies, CoursesDependencies {

    override fun context(): Context

    override fun retrofit(): Retrofit

    override fun getAuthNavigator(): AuthNavigator

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
