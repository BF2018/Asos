package com.asos_codetest.spaceflightapp.model.db

/**
 * This object is used to provide database-related constants to the application
 */
object DatabaseConstants {
    /**
     * Database Name
     */
    const val FLIGHT_DATABASE_NAME = "Spaceflights.db"

    /**
     * Database Version
     */
    const val FLIGHT_DATABASE_VERSION = 2

    /**
     * Export Schema Options
     */
    const val EXPORT_SCHEMA = false

    /**
     * Query to retrieve all flights from the FlightResponse entity
     */
    const val SELECT_ALL_FLIGHTS_QUERY = "SELECT * FROM FlightResponse"

    /**
     * Query to remove all flights from the FlightResponse entity
     */
    const val DELETE_ALL_FLIGHTS_QUERY = "DELETE FROM FlightResponse"


}