package com.asos_codetest.spaceflightapp.di

import com.asos_codetest.spaceflightapp.model.network.FlightApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideUserApi(retrofit: Retrofit) : FlightApiService {
        return retrofit.create(FlightApiService::class.java)
    }

    single { provideUserApi(get()) }
}