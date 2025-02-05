package com.bosta.bostatask.datasource.service


import com.bosta.bostatask.datasource.EndPoint.GET_CITIES_END_POINT
import com.bosta.bostatask.domain.models.CitiesDistrictsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {


    @GET(value = GET_CITIES_END_POINT)
    suspend fun getCities(
        @Query("countryId") uuid: String = "60e4482c7cb7d4bc4849c4d5",
    ): CitiesDistrictsResponse


}