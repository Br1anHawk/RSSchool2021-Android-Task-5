package com.example.rsschool2021_android_task_5.overview_cats

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import com.example.rsschool2021_android_task_5.network.CatsProperty

class RecyclerViewCatImagesAdapter(
    private val listener: RecyclerViewCatImagesListener
): PagingDataAdapter<CatsProperty, CatsPropertyViewHolder>(CatsPropertyDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsPropertyViewHolder {
        return CatsPropertyViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: CatsPropertyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}