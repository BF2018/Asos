package com.asos_codetest.spaceflightapp.model.network

/**
 * Class is used to provide network-related static data to the application
 */
object NetworkConstants {

    /**
     * Provides the BASE URL on which endpoint requests are executed
     */
    const val FLIGHT_BASE_URL = "https://api.spacexdata.com/"

    /**
     * Provides access to the flight data endpoint
     */
    const val FLIGHT_DATA_ENDPOINT = "v4/launches/"

    const val COMPANY_INFO_ENDPOINT = "v3/info"
}