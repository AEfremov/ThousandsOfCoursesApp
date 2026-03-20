package ru.keepitlock.featurecourses.presentation.model

import ru.keepitlock.featurecourses.domain.model.Course

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
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate
    )
}

fun List<Course>.toUi(): List<CourseUi> {
    return map { it.toUi() }
}
