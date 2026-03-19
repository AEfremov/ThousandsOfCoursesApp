package ru.keepitlock.featurecourses.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.keepitlock.featurecourses.domain.model.Course

interface CoursesRepository {
    fun getCourses(): Flow<List<Course>>
}