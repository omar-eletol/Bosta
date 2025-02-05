package com.bosta.bostatask.presentation.ui.states

import com.bosta.bostatask.domain.models.DistrictModel

sealed class DistrictsState {
    data class Success(val data: List<DistrictModel?>?) : DistrictsState()
    data class Error(val throwable: Throwable, val errorBody: String?) : DistrictsState()
    data object Loading : DistrictsState()
    data object Idle : DistrictsState()
    data object Empty : DistrictsState()
}


