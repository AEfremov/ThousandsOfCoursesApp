package ru.keepitlock.featurefavorites.presentation.model

import ru.keepitlock.coreui.ext.formatDate
import ru.keepitlock.coreui.model.CourseUi
import ru.keepitlock.featurefavorites.domain.model.Course
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

//data class CourseUi(
//    /**
//     * Идентификатор курса
//     */
//    val id: Int,
//    /**
//     * Заголовок курса
//     */
//    val title: String,
//    /**
//     * Описание курса
//     */
//    val description: String,
//    /**
//     * Цена курса (с символом ₽)
//     */
//    val price: String,
//    /**
//     * Рейтинг курса (строка для отображения)
//     */
//    val rating: String,
//    /**
//     * Дата начала курса (форматированная строка)
//     */
//    val startDate: String,
//    /**
//     * Признак, добавлен ли курс в избранное
//     */
//    val hasLike: Boolean,
//    /**
//     * Дата публикации курса
//     */
//    val publishDate: String
//)

fun Course.toUi(): CourseUi {
    return CourseUi(
        id = id,
        title = title,
        description = description,
        price = "$price ₽",
        rating = rating.toString(),
        startDate = startDate.formatDate(),
        hasLike = hasLike,
        publishDate = publishDate
    )
}

fun List<Course>.toUi(): List<CourseUi> {
    return map { it.toUi() }
}

//private fun formatDate(dateString: String): String {
//    return try {
//        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//        val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru"))
//        val date = LocalDate.parse(dateString, inputFormatter)
//        date.format(outputFormatter)
//    } catch (e: Exception) {
//        dateString
//    }
//}