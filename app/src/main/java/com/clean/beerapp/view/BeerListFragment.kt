package com.clean.beerapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.clean.beerapp.R
import com.clean.beerapp.ViewModelFactory
import com.clean.beerapp.data.Beer
import com.clean.beerapp.databinding.BeerDetailFragmentBinding
import com.clean.beerapp.databinding.BeerListFragmentBinding
import com.clean.beerapp.domain.BeerRepository
import com.clean.beerapp.view.adapter.BeerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BeerListFragment : Fragment(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener,BeerAdapter.OnAdapter {


    private lateinit var viewModel: BeerListViewModel

    @Inject
    lateinit var  repo:BeerRepository
    private lateinit var binding: BeerListFragmentBinding

    private lateinit var adapter: BeerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BeerListFragmentBinding.inflate(inflater,container,false)
        activity?.setTitle("Beers")

        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this,ViewModelFactory(repo)).get(BeerListViewModel::class.java)
        setupSearchView()
        adapter= BeerAdapter(this)
        observeLivedatas()



    }

    fun setupSearchView(){

        binding.beerSearchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {




        return false

    }
    fun observeLivedatas(){
        viewModel.listBeer.observe(viewLifecycleOwner, Observer {

            if(it.isNotEmpty()){

                binding.rvBeer.visibility=View.VISIBLE
                binding.tvNoBeersFound.visibility=View.GONE

                binding.rvBeer.adapter=adapter
                adapter.setList(it)




            }else{

                binding.rvBeer.visibility=View.GONE
                binding.tvNoBeersFound.visibility=View.VISIBLE
            }


        })



    }

    override fun onQueryTextChange(p0: String?): Boolean {

        if(p0?.length!!>=3){

            viewModel.getBeersByName(p0)
        }



        return true
    }

    override fun onItemClick(beer: Beer) {
        NavHostFragment.findNavController(this).navigate(BeerListFragmentDirections.toBeerDetail(beer))
    }


}