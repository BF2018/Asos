package com.asos_codetest.spaceflightapp.di

import com.asos_codetest.spaceflightapp.viewmodel.FlightDetailsViewModel
import com.asos_codetest.spaceflightapp.viewmodel.FlightViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
   viewModel { FlightViewModel(get()) }
   viewModel {FlightDetailsViewModel()}
}

