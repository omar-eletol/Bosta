package com.bosta.bostatask.presentation.ui.adapters.cities

import com.bosta.bostatask.domain.models.CityModel


interface CitiesOnClickListener {
    fun onItemClick(cityItem: CityModel ,  adapterPosition: Int)
    fun fetchCitiesDistricts()

}