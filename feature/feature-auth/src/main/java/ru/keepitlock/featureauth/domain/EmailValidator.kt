package ru.keepitlock.featureauth.domain

import android.util.Patterns
import javax.inject.Inject

class EmailValidator @Inject constructor() {

    fun isValid(email: String): Boolean {
        if (email.isBlank()) return false

        // Проверка на отсутствие кириллицы
        if (email.any { it in 'а'..'я' || it in 'А'..'Я' }) return false

        // Проверка по маске email
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
