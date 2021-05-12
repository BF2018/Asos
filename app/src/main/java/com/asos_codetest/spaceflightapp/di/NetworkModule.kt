package com.asos_codetest.spaceflightapp.di

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.asos_codetest.spaceflightapp.model.network.NetworkConstants.FLIGHT_BASE_URL
import okhttp3.*
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * Class is used to provide network-based components to the application
 */
val netModule = module {

    //Make a space to cache data from the network
    fun provideCache(application: Application) : Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideOKHttpClient(cache: Cache) : OkHttpClient{
        val okHttpClientBuilder = OkHttpClient.Builder()
            .cache(cache)
            .protocols((Collections.singletonList(Protocol.HTTP_1_1)))

        return okHttpClientBuilder.build()
    }

    fun provideGson() : Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideRetroFit(factory: Gson, okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(FLIGHT_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single { provideCache(androidApplication()) }
    single { provideOKHttpClient(get()) }
    single { provideGson() }
    single { provideRetroFit(get(), get()) }
}