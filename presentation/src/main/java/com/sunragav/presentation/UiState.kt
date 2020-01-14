package com.sunragav.presentation

sealed class UiState {
    object Complete : UiState()
    object Loading : UiState()
    object Empty : UiState()
    class Error(val throwable: Throwable) : UiState()
}