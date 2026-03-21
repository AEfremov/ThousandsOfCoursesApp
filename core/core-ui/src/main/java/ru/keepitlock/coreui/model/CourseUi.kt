package ru.keepitlock.coreui.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

data class CourseUi(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val rating: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)

///**
// * Форматирование даты из yyyy-MM-dd в dd MMMM yyyy
// */
//fun formatDate(dateString: String, locale: Locale = Locale("ru")): String {
//    return try {
//        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//        val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale)
//        val date = LocalDate.parse(dateString, inputFormatter)
//        date.format(outputFormatter)
//    } catch (e: Exception) {
//        dateString
//    }
//}