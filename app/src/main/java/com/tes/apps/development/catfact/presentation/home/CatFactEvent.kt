package com.tes.apps.development.catfact.presentation.home

sealed class CatFactEvent {
    object LoadList : CatFactEvent()
    data class OnSearchQueryChange(val query: String) : CatFactEvent()
}