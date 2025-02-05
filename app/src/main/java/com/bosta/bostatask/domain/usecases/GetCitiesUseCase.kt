package com.bosta.bostatask.domain.usecases


import com.bosta.bostatask.datasource.repository.Repository
import com.bosta.bostatask.domain.models.CitiesDistrictsResponse
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetCitiesUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): CitiesDistrictsResponse = repository.getCities()
}