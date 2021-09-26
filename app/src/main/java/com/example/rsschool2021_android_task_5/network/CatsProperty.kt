package com.example.rsschool2021_android_task_5.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class CatsProperty (
        val id: String = "",
        val url: String = "",
        val breeds: @RawValue List<Breed> = listOf()
): Parcelable