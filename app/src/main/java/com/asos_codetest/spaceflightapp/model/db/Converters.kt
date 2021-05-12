package com.asos_codetest.spaceflightapp.model.db

import androidx.room.TypeConverter
import com.asos_codetest.spaceflightapp.model.companyinfo.Company
import com.google.gson.Gson
import com.asos_codetest.spaceflightapp.model.flight.Flight

/**
 * Class is used by Room database to convert unknown data types
 * to JSON (String format) and then back to their original data types
 * when retrieved from the database.
 */
class Converters{

    /**
     * Function converts list of Flight objects to String
     * @param flights
     * @return String representation of the list
     */
    @TypeConverter
    fun flightListToJson(flights: List<Flight>) : String{
        return Gson().toJson(flights)
    }

    /**
     * Function takes a parameter of string and retrieves a list
     * @param value
     * @return List of Flight objects
     */
    @TypeConverter
    fun flightListFromJson(value: String): List<Flight> {
        return Gson().fromJson(value, Array<Flight>::class.java).toList()
    }

    /**
     * Function converts list of Flight objects to String
     * @param flights
     * @return String representation of the list
     *//*
    @TypeConverter
    fun CompanyInfoToJson(companyInfo : Company) : String{
        return Gson().toJson(companyInfo)
    }

    *//**
     * Function takes a parameter of string and retrieves a list
     * @param value
     * @return List of Flight objects
     *//*
    @TypeConverter
    fun CompanyInfoFromJson(value: String): Company {
        return Gson().fromJson(value, Company::class.java)
    }*/

}