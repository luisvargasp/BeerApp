package com.clean.beerapp.data

import com.clean.beerapp.data.network.BeerApiService
import com.clean.beerapp.domain.BeerRepository
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor (val api :BeerApiService) :BeerRepository {



    override suspend fun getBeerByName(name: String): List<Beer> {

        return api.getBeersByName(name)
    }
}