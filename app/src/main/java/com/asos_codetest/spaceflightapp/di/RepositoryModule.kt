package com.asos_codetest.spaceflightapp.di

import com.asos_codetest.spaceflightapp.model.db.CompanyDao
import com.asos_codetest.spaceflightapp.model.db.FlightDao
import com.asos_codetest.spaceflightapp.model.network.FlightApiService
import com.asos_codetest.spaceflightapp.repository.FlightRepository
import org.koin.dsl.module

val repoModule = module {
    fun provideRepository(flightDao: FlightDao,companyDao: CompanyDao, apiService: FlightApiService) : FlightRepository {
        return FlightRepository(flightDao,apiService,companyDao)
    }

    single { provideRepository(get(), get(),get()) }
}