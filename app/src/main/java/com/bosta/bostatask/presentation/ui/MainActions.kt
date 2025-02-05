package com.bosta.bostatask.presentation.ui

import com.bosta.bostatask.domain.models.CityModel


sealed class MainActions {
    data object FetchCitiesAndDistrictsList : MainActions()
    data class OnItemClicked(val cityItem: CityModel? , val adapterPosition: Int) : MainActions()
    data class SearchCities(val query: String) : MainActions()
    data object ClearSearchText : MainActions()


}