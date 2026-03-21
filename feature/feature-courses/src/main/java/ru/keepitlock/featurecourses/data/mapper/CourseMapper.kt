package ru.keepitlock.featurecourses.data.mapper

import ru.keepitlock.featurecourses.data.model.CourseDto
import ru.keepitlock.featurecourses.domain.model.Course

fun CourseDto.toDomain(): Course {
    return Course(
        id = id,
        title = title,
        description = text,
        price = price,
        rating = rate.toFloatOrNull() ?: 0f,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate
    )
}
fun List<CourseDto>.toDomain(): List<Course> {
    return map { it.toDomain() }
}
