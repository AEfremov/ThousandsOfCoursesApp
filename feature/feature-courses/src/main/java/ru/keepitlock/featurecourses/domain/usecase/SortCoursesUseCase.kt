package ru.keepitlock.featurecourses.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.keepitlock.featurecourses.domain.model.Course
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class SortCoursesUseCase @Inject constructor() {

    suspend operator fun invoke(
        courses: List<Course>,
        isAscending: Boolean
    ): List<Course> = withContext(Dispatchers.Default) {
        courses.sortedWith { course1, course2 ->
            val date1 = parseDate(course1.publishDate)
            val date2 = parseDate(course2.publishDate)
            if (isAscending) {
                date1.compareTo(date2)
            } else {
                date2.compareTo(date1)
            }
        }
    }

    private fun parseDate(dateString: String): Long {
        return try {
            val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            LocalDate.parse(dateString, format)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()
        } catch (e: Exception) {
            0L
        }
    }
}