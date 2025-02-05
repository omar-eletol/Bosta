package com.bosta.bostatask.domain.models

data class CitiesDistrictsResponse(
    val `data`: List<CityModel?>?,
    val message: String?,
    val success: Boolean?
)

data class CityModel(
    val cityCode: String?,
    val cityId: String?,
    val cityName: String?,
    val cityOtherName: String?,
    val districts: List<DistrictModel?>?,
    val dropOffAvailability: Boolean?,
    val pickupAvailability: Boolean?
)

data class DistrictModel(
    val coverage: String?,
    val districtId: String?,
    val districtName: String?,
    val districtOtherName: String?,
    val dropOffAvailability: Boolean?,
    val pickupAvailability: Boolean?,
    val zoneId: String?,
    val zoneName: String?,
    val zoneOtherName: String?
)
