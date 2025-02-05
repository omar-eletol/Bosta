package com.bosta.bostatask.di

import com.bosta.bostatask.BuildConfig
import com.bosta.bostatask.datasource.interceptor.HeaderInterceptor
import com.bosta.bostatask.datasource.interceptor.StatusCodeInterceptor

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {


    //LoggingInterceptor
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
        }

    //HeaderInterceptor
    @Provides
    @Singleton
    fun provideHeaderInterceptor(): HeaderInterceptor = HeaderInterceptor()

    //StatusCodeInterceptor
    @Provides
    @Singleton
    fun provideStatusCodeInterceptor() =
        StatusCodeInterceptor()


}
