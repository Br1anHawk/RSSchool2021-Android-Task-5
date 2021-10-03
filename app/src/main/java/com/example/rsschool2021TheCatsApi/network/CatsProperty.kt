package com.example.rsschool2021TheCatsApi.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatsProperty(
    val id: String = "",
    val url: String = "",
    val breeds: List<Breed> = listOf()
) : Parcelable
