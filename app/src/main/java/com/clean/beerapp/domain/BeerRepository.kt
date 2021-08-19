package com.clean.beerapp.domain

import com.clean.beerapp.data.Beer

interface BeerRepository {

   suspend fun getBeerByName(name:String):List<Beer>
}