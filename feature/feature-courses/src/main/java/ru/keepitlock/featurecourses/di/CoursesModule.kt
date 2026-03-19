package ru.keepitlock.featurecourses.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import ru.keepitlock.coredi.ViewModelKey
import ru.keepitlock.coredi.scope.FeatureScope
import ru.keepitlock.featurecourses.data.api.CoursesApi
import ru.keepitlock.featurecourses.data.repository.CoursesRepositoryImpl
import ru.keepitlock.featurecourses.domain.repository.CoursesRepository
import ru.keepitlock.featurecourses.presentation.CoursesViewModel

@Module
abstract class CoursesModule {

    @Binds
    @FeatureScope
    abstract fun bindCoursesRepository(impl: CoursesRepositoryImpl): CoursesRepository

    @Binds
    @IntoMap
    @ViewModelKey(CoursesViewModel::class)
    abstract fun bindCoursesViewModel(viewModel: CoursesViewModel): ViewModel

    companion object {
        @Provides
        @FeatureScope
        fun provideCoursesApi(retrofit: Retrofit): CoursesApi {
            return retrofit.create(CoursesApi::class.java)
        }
    }
}