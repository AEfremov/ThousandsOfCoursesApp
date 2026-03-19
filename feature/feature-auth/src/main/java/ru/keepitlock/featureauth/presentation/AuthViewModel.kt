package ru.keepitlock.featureauth.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import ru.keepitlock.featureauth.domain.EmailValidator
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val emailValidator: EmailValidator
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

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
        val isEmailValid = emailValidator.isValid(_email.value)
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
