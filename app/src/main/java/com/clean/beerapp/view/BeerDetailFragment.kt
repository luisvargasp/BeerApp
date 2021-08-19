package com.clean.beerapp.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.clean.beerapp.BuildConfig
import com.clean.beerapp.ImageUtil
import com.clean.beerapp.R
import com.clean.beerapp.data.Beer
import com.clean.beerapp.databinding.ActivityMainBinding
import com.clean.beerapp.databinding.BeerDetailFragmentBinding
import java.io.File

class BeerDetailFragment : Fragment() {



    private lateinit var viewModel: BeerDetailViewModel

    private lateinit var binding: BeerDetailFragmentBinding


    private var beer: Beer? =null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = BeerDetailFragmentBinding.inflate(inflater,container,false)
        activity?.setTitle("Detail")


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BeerDetailViewModel::class.java)
        beer= arguments?.let { BeerDetailFragmentArgs.fromBundle(it).beer }

        binding.icShare.setOnClickListener{



      ;

            binding.icShare.visibility=View.INVISIBLE
            val file: File = ImageUtil.saveBitmap(requireContext(),ImageUtil.getBitmapFromView( binding.clContent )!!)!!

            binding.icShare.visibility=View.VISIBLE

            val uri =
                FileProvider.getUriForFile(requireContext(), BuildConfig.APPLICATION_ID, file)
            // create new Intent
            // create new Intent
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
            shareIntent.type = "image/*"
            startActivity(Intent.createChooser(shareIntent, "Compartir constancia"))



        }

        beer?.let {
            setBeerView(beer!!)
        }



    }

    private fun  setBeerView(beer:Beer){
        Glide.with(requireContext())
            .load(beer.image_url)
            .placeholder(R.drawable.ic_launcher_foreground)

            .centerCrop()
            .into(binding.ivBeer)

        binding.tvTitle.text=beer.name
        binding.tvDescription.text=beer.description
        binding.tvAlcoholPercentage.text=String.format("%s %s",beer.abv.toString(),"%")

        var foodPairingText=""
        for(food in beer.food_pairing){

            foodPairingText= foodPairingText+"\n"+food
        }

        binding.tvPairingFood.text=foodPairingText


        var maltText=""

        for(malt in beer.ingredients.malt){
            maltText= maltText+"\n"+malt.name+" "+malt.amount.value+" "+malt.amount.unit
        }
        binding.tvMalt.text=maltText

        var hopsText=""

        for(hop in beer.ingredients.hops){
            hopsText= hopsText+"\n"+hop.name+" "+hop.amount.value+" "+hop.amount.unit
        }
        binding.tvHops.text=hopsText

        binding.tvYeast.text=beer.ingredients.yeast

    }


}