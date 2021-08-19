package com.clean.beerapp.di

import com.clean.beerapp.Constants
import com.clean.beerapp.data.BeerRepositoryImpl
import com.clean.beerapp.data.network.BeerApiService
import com.clean.beerapp.domain.BeerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideRepoImpl( api :BeerApiService):BeerRepository{
        return BeerRepositoryImpl(api )

    }

    @Provides
    @Singleton

    fun provideBeerService():BeerApiService{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL) // Set the API base URL.

            .addConverterFactory(GsonConverterFactory.create())
            .build() // Create the Retrofit instance using the configured values.
            // Create an implementation of the API endpoints defined by the service interface in our case it is RandomDishAPI.
            .create(BeerApiService::class.java)

    }



}