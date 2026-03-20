package ru.keepitlock.featurecourses.di

import dagger.Component
import ru.keepitlock.coredi.scope.FeatureScope
import ru.keepitlock.featurecourses.presentation.CoursesFragment

@FeatureScope
@Component(
    dependencies = [CoursesDependencies::class],
    modules = [CoursesModule::class]
)
interface CoursesComponent {

    fun inject(fragment: CoursesFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: CoursesDependencies): CoursesComponent
    }
}
