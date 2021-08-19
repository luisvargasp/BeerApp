package com.clean.beerapp.data.network

import com.clean.beerapp.data.Beer
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApiService {


        @GET("beers")
        suspend fun getBeersByName(@Query("beer_name") name:String):List<Beer>

}