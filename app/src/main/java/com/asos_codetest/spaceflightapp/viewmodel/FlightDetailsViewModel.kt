package com.asos_codetest.spaceflightapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asos_codetest.spaceflightapp.model.flight.Flight

class FlightDetailsViewModel: ViewModel() {
    // The internal MutableLiveData for the selected character
    private val _selectedFlight = MutableLiveData<Flight>()

    // The external LiveData for the SelectedCharacter
    val selectedFlight: LiveData<Flight>
        get() = _selectedFlight


    fun setFlight(characterProperty: Flight){
        _selectedFlight.value = characterProperty
    }
}