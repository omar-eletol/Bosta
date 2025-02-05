package com.bosta.bostatask.datasource.repository

import com.bosta.bostatask.domain.models.CitiesDistrictsResponse

interface Repository {

    suspend fun getCities(): CitiesDistrictsResponse

}