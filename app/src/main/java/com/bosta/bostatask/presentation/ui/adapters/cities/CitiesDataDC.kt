package com.bosta.bostatask.presentation.ui.adapters.cities


import androidx.recyclerview.widget.DiffUtil
import com.bosta.bostatask.domain.models.CityModel

class CitiesDataDC : DiffUtil.ItemCallback<CityModel>() {
    override fun areItemsTheSame(
        o: CityModel,
        n: CityModel
    ): Boolean =
        o.cityId == n.cityId

    override fun areContentsTheSame(
        o: CityModel,
        n: CityModel
    ): Boolean =
        o == n
}