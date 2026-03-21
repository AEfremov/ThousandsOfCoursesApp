package ru.keepitlock.featurecourses.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.keepitlock.featurecourses.domain.model.Course
import ru.keepitlock.featurecourses.domain.usecase.GetCoursesUseCase
import ru.keepitlock.featurecourses.domain.usecase.SortCoursesUseCase
import ru.keepitlock.featurecourses.presentation.model.CourseUi
import ru.keepitlock.featurecourses.presentation.model.toUi
import javax.inject.Inject

class CoursesViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val sortCoursesUseCase: SortCoursesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<CoursesUiState>(CoursesUiState.Initial)
    val uiState: StateFlow<CoursesUiState> = _uiState.asStateFlow()

    // Состояние сортировки: true = по возрастанию, false = по убыванию
    private var isAscendingSort = false

    // Храним оригинальный список для пересортировки
    private var originalCourses: List<Course> = emptyList()

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
                    originalCourses = courses
                    _uiState.value = CoursesUiState.Success(courses.toUi())
                }
        }
    }

    fun toggleSortOrder() {
        isAscendingSort = !isAscendingSort
        sortCourses()
    }

    private fun sortCourses() {
        viewModelScope.launch {
            val sortedCourses = sortCoursesUseCase(originalCourses, isAscendingSort)
            _uiState.value = CoursesUiState.Success(sortedCourses.toUi())
        }
    }

    fun toggleFavorite(courseId: Int) {
        originalCourses = originalCourses.map { course ->
            if (course.id == courseId) {
                course.copy(hasLike = !course.hasLike)
            } else {
                course
            }
        }

        _uiState.value = CoursesUiState.Success(originalCourses.toUi())
    }
}

sealed class CoursesUiState {
    object Initial : CoursesUiState()
    object Loading : CoursesUiState()
    data class Success(val courses: List<CourseUi>) : CoursesUiState()
    data class Error(val message: String) : CoursesUiState()
}