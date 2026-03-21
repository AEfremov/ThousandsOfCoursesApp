package ru.keepitlock.coredata.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class CourseEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val rating: Float,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)