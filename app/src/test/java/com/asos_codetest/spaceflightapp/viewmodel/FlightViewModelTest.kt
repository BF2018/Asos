package com.asos_codetest.spaceflightapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.asos_codetest.chucknorrisapp.util.CoroutineTestRule
import com.asos_codetest.chucknorrisapp.util.LifeCycleTestOwner
import com.asos_codetest.spaceflightapp.repository.FlightRepository
import com.asos_codetest.spaceflightapp.util.LoadingState
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mockito

class FlightViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var loadingStateObserver: Observer<LoadingState>

    private lateinit var flightRepository: FlightRepository

    private lateinit var flightViewModel: FlightViewModel

    private lateinit var lifeCycleTestOwner: LifeCycleTestOwner


    @Before
    fun setUp() {
        lifeCycleTestOwner = LifeCycleTestOwner()
        lifeCycleTestOwner.onCreate()

        flightRepository = Mockito.mock(FlightRepository::class.java)

        flightViewModel = FlightViewModel(flightRepository)

        loadingStateObserver = Mockito.mock(Observer::class.java) as Observer<LoadingState>
        flightViewModel.loadingState.observe(lifeCycleTestOwner, loadingStateObserver)
    }

    @Test
    fun whenGetFlightData_thenGetFlightsCalled(){
        coroutineTestRule.testDispatcher.runBlockingTest {

            // Given
            lifeCycleTestOwner.onResume()

            Mockito.`when`(flightRepository.getFlights()).thenReturn(null)

            // When
            flightViewModel.getFlightData()

            // Then
            Mockito.verify(flightRepository, Mockito.times(2)).getFlights()
            Mockito.verify(loadingStateObserver).onChanged(LoadingState.LOADING)
        }
    }

    @After
    fun tearDown(){
        lifeCycleTestOwner.onDestroy()
    }
}