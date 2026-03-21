package ru.keepitlock.featurefavorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.keepitlock.coreui.model.CourseUi
import ru.keepitlock.featurefavorites.domain.repository.FavoritesRepository
import ru.keepitlock.featurefavorites.presentation.model.toUi
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val repository: FavoritesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavoritesUiState>(FavoritesUiState.Initial)
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            repository.getFavorites() // use case
                .onStart {
                    _uiState.value = FavoritesUiState.Loading
                }
                .catch { exception ->
                    _uiState.value = FavoritesUiState.Error(
                        exception.message ?: "Произошла ошибка"
                    )
                }
                .collect { courses ->
                    _uiState.value = FavoritesUiState.Success(courses.toUi())
                }
        }
    }

    fun toggleFavorite(courseId: Int) {
        viewModelScope.launch {
            repository.removeFavorite(courseId)
        }
    }
}

/**
 * Состояние UI экрана избранного
 */
sealed class FavoritesUiState {
    object Initial : FavoritesUiState()
    object Loading : FavoritesUiState()
    data class Success(val courses: List<CourseUi>) : FavoritesUiState()
    data class Error(val message: String) : FavoritesUiState()
}