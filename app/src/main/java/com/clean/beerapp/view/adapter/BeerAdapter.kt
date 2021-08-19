package com.clean.beerapp.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clean.beerapp.R
import com.clean.beerapp.data.Beer
import com.clean.beerapp.databinding.BeerItemListBinding

class BeerAdapter(    val listener :OnAdapter) : RecyclerView.Adapter<BeerAdapter.ViewHolder>() {
    private var beers: List<Beer> = listOf<Beer>()


    class ViewHolder(view: BeerItemListBinding) : RecyclerView.ViewHolder(view.root) {
        val image = view.ivBeer
        val title = view.tvTitle
        val description = view.tvDescription

    }

    interface  OnAdapter{

        fun onItemClick(beer:Beer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: BeerItemListBinding = BeerItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val beer = beers.get(position)

        holder.title.text = beer.name
        holder.description.text=beer.description

        Glide.with(holder.itemView.context)
            .load(beer.image_url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            listener.onItemClick(beer)
        }


    }

    override fun getItemCount(): Int {
        return beers.size
    }

    fun setList(list: List<Beer>) {
        beers = list
        notifyDataSetChanged()
    }

}