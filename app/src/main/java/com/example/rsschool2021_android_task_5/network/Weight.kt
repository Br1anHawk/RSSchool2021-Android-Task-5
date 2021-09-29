package com.example.rsschool2021_android_task_5.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weight (
    val imperial: String = "",
    val metric: String = ""
): Parcelable