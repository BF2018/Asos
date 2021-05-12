package com.asos_codetest.spaceflightapp.di

import android.app.Application
import androidx.room.Room
import com.asos_codetest.spaceflightapp.model.db.CompanyDao
import com.asos_codetest.spaceflightapp.model.db.DatabaseConstants.FLIGHT_DATABASE_NAME
import com.asos_codetest.spaceflightapp.model.db.FlightDao
import com.asos_codetest.spaceflightapp.model.db.FlightDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application) : FlightDatabase {
        return Room.databaseBuilder(application, FlightDatabase::class.java, FLIGHT_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideFlightDao(database: FlightDatabase) : FlightDao {
        return database.flightDao
    }

    fun provideCompanyDao(database: FlightDatabase) : CompanyDao {
        return database.companyDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideFlightDao(get()) }
    single { provideCompanyDao(get()) }
}