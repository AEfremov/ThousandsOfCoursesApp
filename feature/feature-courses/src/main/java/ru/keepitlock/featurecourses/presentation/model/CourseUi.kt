package ru.keepitlock.featurecourses.presentation.model

import ru.keepitlock.featurecourses.domain.model.Course
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

fun Course.toUi(): CourseUi {
    return CourseUi(
        id = id,
        title = title,
        description = description,
        price = "$price ₽",
        rating = rating.toString(),
        startDate = formatDate(startDate),
        hasLike = hasLike,
        publishDate = publishDate
    )
}

fun List<Course>.toUi(): List<CourseUi> {
    return map { it.toUi() }
}

private fun formatDate(dateString: String): String {
    return try {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.getDefault())
        val date = LocalDate.parse(dateString, inputFormatter)
        date.format(outputFormatter)
    } catch (e: Exception) {
        dateString
    }
}