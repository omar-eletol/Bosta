package com.bosta.bostatask.presentation.ui

import com.bosta.bostatask.domain.models.CityModel


sealed class MainActions {
    data object FetchCitiesAndDistrictsList : MainActions()
    data class OnItemClicked(val cityItem: CityModel?) : MainActions()


}