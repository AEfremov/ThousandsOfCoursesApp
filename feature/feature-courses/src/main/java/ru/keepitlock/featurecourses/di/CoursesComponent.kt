package ru.keepitlock.featurecourses.di

import dagger.Component
import ru.keepitlock.coredi.scope.FeatureScope
import ru.keepitlock.featurecourses.presentation.CoursesFragment
import ru.keepitlock.featurecourses.presentation.MainActivity

@FeatureScope
@Component(
    dependencies = [CoursesDependencies::class],
    modules = [CoursesModule::class]
)
interface CoursesComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: CoursesFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: CoursesDependencies): CoursesComponent
    }
}
