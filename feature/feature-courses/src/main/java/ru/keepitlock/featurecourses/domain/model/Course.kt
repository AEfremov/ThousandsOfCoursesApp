package ru.keepitlock.featurecourses.domain.model

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
     * Цена курса
     */
    val price: String,

    /**
     * Рейтинг курса
     */
    val rating: Float,

    /**
     * Дата начала курса
     */
    val startDate: String,

    /**
     * Признак, добавлен ли курс в избранное
     */
    val hasLike: Boolean,

    /**
     * Дата публикации курса
     */
    val publishDate: String
)
