package ru.keepitlock.coreui.ext

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Форматирование даты из yyyy-MM-dd в dd MMMM yyyy
 */
fun String.formatDate(): String {
    return try {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.getDefault())
        val date = LocalDate.parse(this, inputFormatter)
        date.format(outputFormatter)
    } catch (e: Exception) {
        this
    }
}