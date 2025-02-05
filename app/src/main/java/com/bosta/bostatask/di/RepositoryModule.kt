package com.bosta.bostatask.di


import com.bosta.bostatask.datasource.repository.Repository
import com.bosta.bostatask.datasource.repository.RepositoryImp
import com.bosta.bostatask.datasource.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): Repository {
        return RepositoryImp(apiService = apiService)
    }

}
