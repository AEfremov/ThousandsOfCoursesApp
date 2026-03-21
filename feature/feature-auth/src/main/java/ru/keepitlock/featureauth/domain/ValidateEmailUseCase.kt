package ru.keepitlock.featureauth.domain

import android.util.Patterns
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {

    operator fun invoke(email: String): Boolean {
        if (email.isBlank()) return false
        if (email.any { it in 'а'..'я' || it in 'А'..'Я' }) return false
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
