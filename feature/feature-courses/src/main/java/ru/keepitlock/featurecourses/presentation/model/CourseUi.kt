package ru.keepitlock.featurecourses.presentation.model

import ru.keepitlock.coreui.ext.formatDate
import ru.keepitlock.coreui.model.CourseUi
import ru.keepitlock.featurecourses.domain.model.Course

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
