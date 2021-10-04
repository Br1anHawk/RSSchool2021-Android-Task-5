package com.example.rsschool2021TheCatsApi.overviewCats

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.rsschool2021TheCatsApi.network.entities.CatsProperty

class RecyclerViewCatImagesAdapter(
    private val listener: RecyclerViewCatImagesListener
) : PagingDataAdapter<CatsProperty, CatsPropertyViewHolder>(CatsPropertyDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsPropertyViewHolder {
        return CatsPropertyViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: CatsPropertyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
