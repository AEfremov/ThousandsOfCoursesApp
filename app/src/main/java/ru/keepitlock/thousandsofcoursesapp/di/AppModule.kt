package ru.keepitlock.thousandsofcoursesapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.keepitlock.featureauth.presentation.AuthNavigator
import ru.keepitlock.thousandsofcoursesapp.navigation.AppNavigator
import javax.inject.Singleton

@Module
class AppModule {

//    @Provides
//    @Singleton
//    fun provideContext(context: Context): Context = context.applicationContext

    @Provides
    @Singleton
    fun provideAuthNavigator(context: Context): AuthNavigator {
        return AppNavigator(context.applicationContext)
    }
}
