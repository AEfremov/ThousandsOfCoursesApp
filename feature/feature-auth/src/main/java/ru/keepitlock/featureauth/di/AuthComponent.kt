package ru.keepitlock.featureauth.di

import dagger.Component
import ru.keepitlock.coredi.scope.FeatureScope
import ru.keepitlock.featureauth.presentation.AuthActivity

@FeatureScope
@Component(
    dependencies = [AuthDependencies::class],
    modules = [AuthModule::class]
)
interface AuthComponent {

    fun inject(activity: AuthActivity)

    @Component.Factory
    interface Factory {
        fun create(dependencies: AuthDependencies): AuthComponent
    }
}
