package com.bosta.bostatask.di

import com.bosta.bostatask.BuildConfig.baseUrl
import com.bosta.bostatask.datasource.EndPoint
import com.bosta.bostatask.datasource.interceptor.HeaderInterceptor
import com.bosta.bostatask.datasource.interceptor.StatusCodeInterceptor
import com.bosta.bostatask.datasource.service.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    //OkHttpClient
    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: HeaderInterceptor,
        statusCodeInterceptor: StatusCodeInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder().apply {

        connectTimeout(EndPoint.timeOut, TimeUnit.SECONDS)
        readTimeout(EndPoint.timeOut, TimeUnit.SECONDS)
        writeTimeout(EndPoint.timeOut, TimeUnit.SECONDS)

        addInterceptor(headerInterceptor)
        addInterceptor(statusCodeInterceptor)
        addInterceptor(loggingInterceptor)


    }.build()


    //Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        coroutineCallAdapterFactory: CoroutineCallAdapterFactory
    ): Retrofit = Retrofit.Builder().client(okHttpClient).baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(coroutineCallAdapterFactory).build()

    //ApiService
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)


}
