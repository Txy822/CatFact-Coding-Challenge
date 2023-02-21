package com.tes.apps.development.catfact.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tes.apps.development.catfact.domain.use_case.CatFactUseCase
import com.tes.apps.development.catfact.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatFactViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val favoriteUseCase: CatFactUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(CatFactUiState())

    val uiState: StateFlow<CatFactUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    fun onEvent(event: CatFactEvent) {
        when (event) {
            is CatFactEvent.LoadList -> {
                getCatFactList()
            }
            is CatFactEvent.OnSearchQueryChange -> {
                _uiState.update { it.copy(searchQuery = event.query) }
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCatFactList()
                }
            }
        }
    }

    fun getCatFactList(
        query: String = _uiState.value.searchQuery.lowercase()
    ) {
        viewModelScope.launch(dispatcher) { //was Dispatchers.main
            favoriteUseCase.getCatFactList(query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                _uiState.update { it.copy(catFacts = listings) }
                            }
                            _uiState.value = _uiState.value.copy()
                        }
                        is Resource.Error -> {
                            _uiState.update { it.copy(error = "Error message") }
                        }
                        is Resource.Loading -> {
                            _uiState.update { it.copy(isLoading = result.isLoading) }
                        }
                    }
                }
        }
    }
}
