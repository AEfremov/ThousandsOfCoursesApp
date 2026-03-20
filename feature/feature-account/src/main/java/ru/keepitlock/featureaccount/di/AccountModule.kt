package ru.keepitlock.featureaccount.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.keepitlock.coredi.ViewModelKey
import ru.keepitlock.coredi.scope.FeatureScope
import ru.keepitlock.featureaccount.presentation.AccountViewModel

@Module
abstract class AccountModule {

//    @Binds
//    @FeatureScope
//    abstract fun bindAccountRepository(impl: AccountRepositoryImpl): AccountRepository

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(viewModel: AccountViewModel): ViewModel

    companion object {
//        @Provides
//        @FeatureScope
//        fun provideAccountApi(retrofit: Retrofit): AccountApi {
//            return retrofit.create(AccountApi::class.java)
//        }
    }
}