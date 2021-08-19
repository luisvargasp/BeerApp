package com.clean.beerapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clean.beerapp.data.Beer
import com.clean.beerapp.domain.BeerRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

class BeerListViewModel @Inject constructor(var  repo:BeerRepository) : ViewModel() {



    private val _listBeer=MutableLiveData<List<Beer>>()
    val listBeer :LiveData<List<Beer>> = _listBeer

    private val _error=MutableLiveData<Exception>()
    val error :LiveData<Exception> = _error




    fun getBeersByName(name :String){

        viewModelScope.launch {
            try {

                _listBeer.value=repo.getBeerByName(name)
            }catch (e:Exception){

                _error.value=e


            }








        }
    }




}