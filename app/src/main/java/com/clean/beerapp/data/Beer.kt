package com.clean.beerapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class Beer(
    val abv: Double,
    val attenuation_level: Double,
    val boil_volume: BoilVolume,
    val brewers_tips: String,
    val contributed_by: String,
    val description: String,
    val ebc: Double,
    val first_brewed: String,
    val food_pairing: List<String>,
    val ibu: Double,
    val id: Int,
    val image_url: String,
    val ingredients: Ingredients,
    val method: Method,
    val name: String,
    val ph: Double,
    val srm: Double,
    val tagline: String,
    val target_fg: Double,
    val target_og: Double,
    val volume: Volume
):Parcelable
@Parcelize
data class BoilVolume(
    val unit: String,
    val value: Int
):Parcelable
@Parcelize

data class Ingredients(
    val malt: List<Malt>,
    val hops: List<Malt>,

    val yeast: String
):Parcelable
@Parcelize

data class Method(
    val fermentation: Fermentation,
    val mash_temp: List<MashTemp>,
):Parcelable
@Parcelize

data class Volume(
    val unit: String,
    val value: Int
):Parcelable
@Parcelize

data class Malt(
    val amount: Amount,
    val name: String
):Parcelable
@Parcelize

data class Amount(
    val unit: String,
    val value: Double
):Parcelable

@Parcelize


data class Fermentation(
    val temp: Temp
):Parcelable

@Parcelize


data class MashTemp(
    val duration: Int,
    val temp: TempX
):Parcelable

@Parcelize


data class Temp(
    val unit: String,
    val value: Double
):Parcelable

@Parcelize


data class TempX(
    val unit: String,
    val value: Int
):Parcelable


