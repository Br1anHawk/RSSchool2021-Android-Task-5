package com.example.rsschool2021TheCatsApi.overviewCats

import com.example.rsschool2021TheCatsApi.network.CatsProperty

interface RecyclerViewCatImagesListener {
    fun detailedInfoAboutCat(catsProperty: CatsProperty)
}
