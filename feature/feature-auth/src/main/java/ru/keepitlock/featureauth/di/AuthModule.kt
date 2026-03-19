package ru.keepitlock.featureauth.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.keepitlock.coredi.ViewModelKey
import ru.keepitlock.featureauth.domain.EmailValidator
import ru.keepitlock.featureauth.presentation.AuthViewModel

@Module
abstract class AuthModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
    companion object {
        @Provides
        fun provideEmailValidator(): EmailValidator = EmailValidator()
    }
}
