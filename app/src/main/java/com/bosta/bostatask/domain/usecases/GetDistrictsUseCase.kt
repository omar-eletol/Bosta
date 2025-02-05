package com.bosta.bostatask.domain.usecases


import com.bosta.bostatask.domain.models.CityModel
import com.bosta.bostatask.domain.models.DistrictModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetDistrictsUseCase @Inject constructor() {
    operator fun invoke(cityItem: CityModel?): List<DistrictModel?>? = cityItem?.districts
}