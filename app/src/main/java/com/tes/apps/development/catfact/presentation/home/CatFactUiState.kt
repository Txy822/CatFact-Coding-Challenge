package com.tes.apps.development.catfact.presentation.home

import com.tes.apps.development.catfact.domain.model.CatFactModel

data class CatFactUiState (
    val catFacts: List<CatFactModel> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val error: String = ""
)