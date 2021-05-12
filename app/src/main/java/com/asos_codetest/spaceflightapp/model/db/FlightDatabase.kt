package com.asos_codetest.spaceflightapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.asos_codetest.spaceflightapp.model.companyinfo.Company
import com.asos_codetest.spaceflightapp.model.db.DatabaseConstants.EXPORT_SCHEMA
import com.asos_codetest.spaceflightapp.model.db.DatabaseConstants.FLIGHT_DATABASE_VERSION
import com.asos_codetest.spaceflightapp.model.flight.FlightResponse

@Database(entities = [FlightResponse::class,Company::class], version = FLIGHT_DATABASE_VERSION, exportSchema = EXPORT_SCHEMA)
@TypeConverters(Converters::class)
abstract class FlightDatabase : RoomDatabase() {
    abstract val flightDao: FlightDao
    abstract val companyDao : CompanyDao
}