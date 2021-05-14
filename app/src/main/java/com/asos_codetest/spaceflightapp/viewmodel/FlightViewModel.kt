package com.asos_codetest.spaceflightapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.asos_codetest.spaceflightapp.model.flight.Flight
import com.asos_codetest.spaceflightapp.repository.FlightRepository
import com.asos_codetest.spaceflightapp.util.LoadingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 * Class is used to provide communications between the Repository and View classes
 */
class FlightViewModel(
    private val flightRepository: FlightRepository
) : ViewModel()
{
    /**
     * Listen for updates from the FlightRepository class
     */
    val flightData = flightRepository.flightData

    /**
     * Listen for updates from the FlightRepository class
     */
     val companyInfo = flightRepository.companyInfo

    /**
     * Listen for changes in the API call loading state
     */
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
    get() = _loadingState

    lateinit var allItemSearch : LiveData<List<Flight>>
    var itemSearch =  MutableLiveData("%")

    init {
        allItemSearch = Transformations.switchMap(itemSearch) {search->
           flightRepository.getSearchedItem(search)
        }
    }

    /**
     * Create a coroutine scoped to this ViewModel class
     */
    val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    /**
     * On initialisation, request updated flight data from the FlightRepository
     */
    init {
        getCompanyInfo()
        getFlightData()
    }

    /**
     * Function requests the FlightRepository to update its data sources
     */
    fun getFlightData(){
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                flightRepository.getFlights()
                _loadingState.value = LoadingState.LOADED
            }
            catch (exception: HttpException){
                _loadingState.value = LoadingState.logError(exception.message())
            }
        }
    }

    //for display data in second fragment
    // LiveData to handle navigation to the selected Character
    private val _navigateToSelectedFlight = MutableLiveData<Flight?>()
    val navigateToSelectedFlight: MutableLiveData<Flight?>
        get() = _navigateToSelectedFlight

    fun displayFlightDetails(characterProperty: Flight) {
        _navigateToSelectedFlight.value = characterProperty
    }

    /**
     * After the navigation has taken place, make sure navigateToSelectedProperty is set to null
     */
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedFlight.value = null
    }

    private fun getCompanyInfo(){
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                flightRepository.getCompanyInfoFromApi()
                _loadingState.value = LoadingState.LOADED
            }
            catch (exception: HttpException){
                _loadingState.value = LoadingState.logError(exception.message())
            }
        }
    }


    fun searchFlight(newSearch : String){
        val filter = when {
            newSearch.isEmpty() -> "%"
            else -> "%$newSearch%"
        }
        itemSearch.postValue(filter)
    }
}
