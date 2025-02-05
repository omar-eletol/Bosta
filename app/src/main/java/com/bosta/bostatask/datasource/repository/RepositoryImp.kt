package com.bosta.bostatask.datasource.repository

import com.bosta.bostatask.datasource.service.ApiService
import com.bosta.bostatask.domain.models.CitiesDistrictsResponse
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val apiService: ApiService) : Repository {
    override suspend fun getCities(): CitiesDistrictsResponse {
        return apiService.getCities()
    }
}