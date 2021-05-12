package com.asos_codetest.spaceflightapp.util.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.asos_codetest.spaceflightapp.repository.FlightRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Class is used by the WorkManager to schedule work to be completed in the background
 * even when the app is not running or has gone into the background
 */
class FlightWorker (appContext:Context, parameters: WorkerParameters) :
    CoroutineWorker(appContext, parameters), KoinComponent {

    private val flightRepository: FlightRepository by inject()

    companion object {
        const val WORK_LOCATION = "com.asos_codetest.spaceflightapp.util.background.FlightWorker"
    }

    /**
     * Sync the backend API data with local database even if user is not using the app or device restarts
     */
    override suspend fun doWork(): Result {
        try {
            flightRepository.getFlights()
        } catch (e: Exception) {
            return Result.retry()
        }
        return Result.success()
    }
}