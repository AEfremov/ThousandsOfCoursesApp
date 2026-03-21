package ru.keepitlock.featurecourses.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.keepitlock.featurecourses.domain.model.Course
import ru.keepitlock.featurecourses.domain.repository.CoursesRepository
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val repository: CoursesRepository
) {

    operator fun invoke(): Flow<List<Course>> {
        return repository.getCourses()
    }
}
