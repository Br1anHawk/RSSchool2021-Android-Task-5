package com.example.rsschool2021TheCatsApi.network.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Breed(
    val weight: Weight = Weight("", ""),
    val id: String = "",
    val name: String = "",
    val temperament: String = "",
    val origin: String = "",
    val description: String = "",
    @Json(name = "life_span")
    val lifeSpan: String = "",
    val indoor: Int = 0,
    val lap: Int = 0,
    @Json(name = "alt_names")
    val altNames: String = "",
    val adaptability: Int = 0,
    @Json(name = "affection_level")
    val affectionLevel: Int = 0,
    @Json(name = "child_friendly")
    val childFriendly: Int = 0,
    @Json(name = "dog_friendly")
    val dogFriendly: Int = 0,
    @Json(name = "energy_level")
    val energyLevel: Int = 0,
    val grooming: Int = 0,
    @Json(name = "health_issues")
    val healthIssues: Int = 0,
    val intelligence: Int = 0,
    @Json(name = "shedding_level")
    val sheddingLevel: Int = 0,
    @Json(name = "social_needs")
    val socialNeeds: Int = 0,
    @Json(name = "stranger_friendly")
    val strangerFriendly: Int = 0,
    val vocalisation: Int = 0,
    val experimental: Int = 0,
    val hairless: Int = 0,
    val natural: Int = 0,
    val rare: Int = 0,
    val rex: Int = 0,
    @Json(name = "suppressed_tail")
    val suppressedTail: Int = 0,
    @Json(name = "short_legs")
    val shortLegs: Int = 0,
    @Json(name = "wikipedia_url")
    val wikipediaUrl: String = "",
    val hypoallergenic: Int = 0
) : Parcelable
