package com.example.rsschool2021TheCatsApi.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Breed(
    val weight: Weight = Weight("", ""),
    val id: String = "",
    val name: String = "",
    val temperament: String = "",
    val origin: String = "",
    val description: String = "",
    val life_span: String = "",
    val indoor: Int = 0,
    val lap: Int = 0,
    val alt_names: String = "",
    val adaptability: Int = 0,
    val affection_level: Int = 0,
    val child_friendly: Int = 0,
    val dog_friendly: Int = 0,
    val energy_level: Int = 0,
    val grooming: Int = 0,
    val health_issues: Int = 0,
    val intelligence: Int = 0,
    val shedding_level: Int = 0,
    val social_needs: Int = 0,
    val stranger_friendly: Int = 0,
    val vocalisation: Int = 0,
    val experimental: Int = 0,
    val hairless: Int = 0,
    val natural: Int = 0,
    val rare: Int = 0,
    val rex: Int = 0,
    val suppressed_tail: Int = 0,
    val short_legs: Int = 0,
    val wikipedia_url: String = "",
    val hypoallergenic: Int = 0
) : Parcelable
