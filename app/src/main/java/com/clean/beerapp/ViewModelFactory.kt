package com.clean.beerapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clean.beerapp.domain.BeerRepository

class ViewModelFactory(val repository: BeerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(BeerRepository::class.java).newInstance(repository)
    }


}