package com.asos_codetest.spaceflightapp.util

import android.app.Application
import androidx.work.*
import com.asos_codetest.spaceflightapp.di.*
import com.asos_codetest.spaceflightapp.util.background.FlightWorker
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.util.concurrent.TimeUnit

class SpaceFlightApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin()
        startWorkManager()
    }

    private fun startKoin(){
        startKoin {
            androidContext(this@SpaceFlightApp)
            androidLogger(Level.ERROR)
            modules(netModule, apiModule, databaseModule, repoModule, viewModelModule)
        }
    }

    private fun startWorkManager(){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(false)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(false)
            .build()

        //Set the frequency at which the job should be executed
        val repeatingRequest = PeriodicWorkRequestBuilder<FlightWorker>(1, TimeUnit.HOURS)
            //Add the device constraints
            .setConstraints(constraints)
            .build()

        //Schedule the work to be completed
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            FlightWorker.WORK_LOCATION,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }
}