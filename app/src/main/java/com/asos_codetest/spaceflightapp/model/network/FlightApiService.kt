package com.asos_codetest.spaceflightapp.model.network

import com.asos_codetest.spaceflightapp.model.companyinfo.Company
import com.asos_codetest.spaceflightapp.model.flight.Flight
import com.asos_codetest.spaceflightapp.model.network.NetworkConstants.COMPANY_INFO_ENDPOINT
import com.asos_codetest.spaceflightapp.model.network.NetworkConstants.FLIGHT_DATA_ENDPOINT
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 *  Interface provides access to the Space X API Service
 */
interface  FlightApiService {

    /**
     * Function retrieves a list of flights launched by SpaceX
     * @return List of Flight objects
     */
    @GET(FLIGHT_DATA_ENDPOINT)
    fun getAllFlights() : Deferred<List<Flight>>

    @GET(COMPANY_INFO_ENDPOINT)
    fun getCompanyInfo() : Deferred<Company?>
}