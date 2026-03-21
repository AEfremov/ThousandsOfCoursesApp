package ru.keepitlock.featureaccount.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.keepitlock.coredi.ViewModelKey
import ru.keepitlock.featureaccount.presentation.AccountViewModel

@Module
abstract class AccountModule {

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(viewModel: AccountViewModel): ViewModel
}