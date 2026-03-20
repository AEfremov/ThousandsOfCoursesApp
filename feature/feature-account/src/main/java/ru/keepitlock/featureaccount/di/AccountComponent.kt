package ru.keepitlock.featureaccount.di

import dagger.Component
import ru.keepitlock.coredi.scope.FeatureScope
import ru.keepitlock.featureaccount.presentation.AccountFragment

@FeatureScope
@Component(
    dependencies = [AccountDependencies::class],
    modules = [AccountModule::class]
)
interface AccountComponent {

    fun inject(fragment: AccountFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: AccountDependencies): AccountComponent
    }
}