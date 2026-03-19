package ru.keepitlock.featurecourses.domain.model

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val rating: Float,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)
