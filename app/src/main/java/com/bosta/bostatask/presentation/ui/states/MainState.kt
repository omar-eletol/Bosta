package com.bosta.bostatask.presentation.ui.states

import com.bosta.bostatask.domain.models.CitiesDistrictsResponse

sealed class MainState {
    data class Success(val data: CitiesDistrictsResponse) : MainState()
    data class Error(val throwable: Throwable, val errorBody: String?) : MainState()
    data object Loading : MainState()
    data object Idle : MainState()
    data object Empty : MainState()
}


