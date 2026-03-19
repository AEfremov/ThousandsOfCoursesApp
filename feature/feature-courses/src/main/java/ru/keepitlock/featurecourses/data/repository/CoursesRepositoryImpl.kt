package ru.keepitlock.featurecourses.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.keepitlock.featurecourses.data.api.CoursesApi
import ru.keepitlock.featurecourses.data.mapper.toDomain
import ru.keepitlock.featurecourses.domain.model.Course
import ru.keepitlock.featurecourses.domain.repository.CoursesRepository
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val api: CoursesApi
) : CoursesRepository {

    override fun getCourses(): Flow<List<Course>> = flow {
        val response = api.getCourses()
        emit(response.courses.toDomain())
    }
}
