package com.example.rsschool2021TheCatsApi.overviewCats

import com.example.rsschool2021TheCatsApi.network.entities.CatsProperty

interface RecyclerViewCatImagesListener {
    fun detailedInfoAboutCat(catsProperty: CatsProperty)
}
