package ru.keepitlock.featurefavorites.domain.model

import ru.keepitlock.coredata.model.entity.CourseEntity

data class Course(
    /**
     * Идентификатор курса
     */
    val id: Int,
    /**
     * Заголовок курса
     */
    val title: String,
    /**
     * Описание курса
     */
    val description: String,
    /**
     * Цена курса (без символа ₽)
     */
    val price: String,
    /**
     * Рейтинг курса
     */
    val rating: Float,
    /**
     * Дата начала курса (формат: yyyy-MM-dd)
     */
    val startDate: String,
    /**
     * Признак, добавлен ли курс в избранное
     */
    val hasLike: Boolean,
    /**
     * Дата публикации курса (формат: yyyy-MM-dd)
     */
    val publishDate: String
)

fun CourseEntity.toDomain(): Course {
    return Course(
        id = id,
        title = title,
        description = description,
        price = price,
        rating = rating,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate
    )
}