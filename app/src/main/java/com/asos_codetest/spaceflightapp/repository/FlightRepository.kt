package com.asos_codetest.spaceflightapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.asos_codetest.spaceflightapp.model.companyinfo.Company
import com.asos_codetest.spaceflightapp.model.db.CompanyDao
import com.asos_codetest.spaceflightapp.model.db.FlightDao
import com.asos_codetest.spaceflightapp.model.flight.Flight
import com.asos_codetest.spaceflightapp.model.flight.FlightResponse
import com.asos_codetest.spaceflightapp.model.network.FlightApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber

/**
 * Class is used to communicate with the API and local Room database
 */
class FlightRepository(
    private val flightDao: FlightDao,
    private val flightApiService: FlightApiService,
    private val companyDao: CompanyDao,
) {

    /**
     * Function retrieves flight data from the API services and caches it to a local
     * Room database
     */
    suspend fun getFlights() {
        withContext(Dispatchers.IO) {
            try {
                val flightData = flightApiService.getAllFlights().await()
                storeFlightData(FlightResponse(1, flightData))
            } catch (exception: HttpException) {
                Log.d("HTTP-EX", exception.message())
            }
        }
    }

    /**
     * Save FlightResponse data to local database
     * @param flightResponse
     */
    fun storeFlightData(flightResponse: FlightResponse) {
        flightDao.insertFlights(flightResponse)
    }

    /**
     * Listen for changes in the FlightResponse table and reports them to any registered
     * ViewModel classes
     */
    val flightData = flightDao.getAllFlights()


    /**
     * Function retrieves company info from the API services and caches it to a local
     * Room database
     */
    suspend fun getCompanyInfoFromApi() {
        withContext(Dispatchers.IO) {
            try {
                val companyData = flightApiService.getCompanyInfo().await()
                companyData?.let {
                    companyDao.insertCompanyInfo(it)
                }

            }catch (exception: HttpException) {
                Timber.d(exception.message())
            }
        }
    }

    /**
     * Listen for changes in the Company table and reports them to any registered
     * ViewModel classes
     */
    val companyInfo = companyDao.getCompanyInfo()



    /**
     * For searching flight
     * */
    /*fun getSearchedItem(filter : String) : LiveData<List<Flight>>{
        return flightDao
    }*/
}

