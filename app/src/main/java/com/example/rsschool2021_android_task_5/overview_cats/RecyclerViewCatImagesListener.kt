package com.example.rsschool2021_android_task_5.overview_cats

import com.example.rsschool2021_android_task_5.network.CatsProperty

interface RecyclerViewCatImagesListener {
    fun detailedInfoAboutCat(catsProperty: CatsProperty)
}