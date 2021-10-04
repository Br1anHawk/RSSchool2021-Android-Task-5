package com.example.rsschool2021TheCatsApi.network.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weight(
    val imperial: String = "",
    val metric: String = ""
) : Parcelable
