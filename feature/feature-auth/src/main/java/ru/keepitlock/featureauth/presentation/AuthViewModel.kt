package ru.keepitlock.featureauth.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.keepitlock.featureauth.domain.ValidateEmailUseCase
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase
) : ViewModel() {

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    private val _isLoginEnabled = MutableStateFlow(false)
    val isLoginEnabled: StateFlow<Boolean> = _isLoginEnabled.asStateFlow()

    private val _loginEvent = MutableStateFlow<LoginEvent?>(null)
    val loginEvent: StateFlow<LoginEvent?> = _loginEvent.asStateFlow()

    fun onEmailChanged(email: String) {
        val filteredEmail = email.filter { it !in 'а'..'я' && it !in 'А'..'Я' }
        _email.value = filteredEmail
        validateForm()
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
        validateForm()
    }

    private fun validateForm() {
        val isEmailValid = validateEmailUseCase(_email.value)
        val isPasswordFilled = _password.value.isNotBlank()
        _isLoginEnabled.value = isEmailValid && isPasswordFilled
    }

    fun onLoginClicked() {
        if (_isLoginEnabled.value) {
            _loginEvent.value = LoginEvent.NavigateToMain
        }
    }

    fun onLoginEventHandled() {
        _loginEvent.value = null
    }

    sealed class LoginEvent {
        object NavigateToMain : LoginEvent()
    }
}
