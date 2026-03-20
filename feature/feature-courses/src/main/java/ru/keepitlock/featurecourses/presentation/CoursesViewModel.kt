package ru.keepitlock.featurecourses.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.keepitlock.featurecourses.domain.usecase.GetCoursesUseCase
import ru.keepitlock.featurecourses.presentation.model.CourseUi
import ru.keepitlock.featurecourses.presentation.model.toUi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class CoursesViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CoursesUiState>(CoursesUiState.Initial)
    val uiState: StateFlow<CoursesUiState> = _uiState.asStateFlow()

    // Состояние сортировки: true = по возрастанию, false = по убыванию
    private var isAscendingSort = false

    // Храним оригинальный список для пересортировки
    private var originalCourses: List<CourseUi> = emptyList()

    init {
        loadCourses()
    }

    fun loadCourses() {
        viewModelScope.launch {
            getCoursesUseCase()
                .onStart {
                    _uiState.value = CoursesUiState.Loading
                }
                .catch { exception ->
                    _uiState.value = CoursesUiState.Error(
                        exception.message ?: "Произошла ошибка"
                    )
                }
                .collect { courses ->
                    originalCourses = courses.toUi()
                    _uiState.value = CoursesUiState.Success(courses.toUi())
                }
        }
    }

    fun toggleSortOrder() {
        isAscendingSort = !isAscendingSort
        sortCourses()
    }

    private fun sortCourses() {
        println(originalCourses)
        val sortedCourses = originalCourses.sortedWith { course1, course2 ->
            val date1 = parseDate(course1.publishDate)
            val date2 = parseDate(course2.publishDate)

            if (isAscendingSort) {
                date1.compareTo(date2)
            } else {
                date2.compareTo(date1)
            }
        }
        println(sortedCourses)

        _uiState.value = CoursesUiState.Success(sortedCourses)
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

    fun toggleFavorite(courseId: Int) {
        val currentState = _uiState.value as? CoursesUiState.Success ?: return
        val updatedCourses = currentState.courses.map { course ->
            if (course.id == courseId) {
                course.copy(hasLike = !course.hasLike)
            } else {
                course
            }
        }

        originalCourses = updatedCourses
        _uiState.value = CoursesUiState.Success(updatedCourses)
    }
}

sealed class CoursesUiState {
    object Initial : CoursesUiState()
    object Loading : CoursesUiState()
    data class Success(val courses: List<CourseUi>) : CoursesUiState()
    data class Error(val message: String) : CoursesUiState()
}