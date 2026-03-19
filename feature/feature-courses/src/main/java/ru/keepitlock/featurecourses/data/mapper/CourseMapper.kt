package ru.keepitlock.featurecourses.data.mapper

import ru.keepitlock.featurecourses.data.model.CourseDto
import ru.keepitlock.featurecourses.domain.model.Course
import javax.inject.Inject

class CourseMapper @Inject constructor() {

    fun toDomain(dto: CourseDto): Course {
        return Course(
            id = dto.id,
            title = dto.title,
            description = dto.text,
            price = dto.price,
            rating = dto.rate.toFloatOrNull() ?: 0f,
            startDate = dto.startDate,
            hasLike = dto.hasLike,
            publishDate = dto.publishDate
        )
    }

    fun toDomainList(dtoList: List<CourseDto>): List<Course> {
        return dtoList.map { toDomain(it) }
    }
}

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
