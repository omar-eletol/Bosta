package com.bosta.bostatask.domain.models

data class CitiesDistrictsResponse(
    val `data`: List<CityModel>?,
    val message: String?,
    val success: Boolean?
)

data class CityModel(
    val cityId: String?,
    val cityName: String?,
    val districts: List<DistrictModel>?,
)

data class DistrictModel(
    val districtId: String?,
    val districtName: String?,
    val zoneName: String?,
)
