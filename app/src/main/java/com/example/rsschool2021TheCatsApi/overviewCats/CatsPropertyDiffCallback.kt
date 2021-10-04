package com.example.rsschool2021TheCatsApi.overviewCats

import androidx.recyclerview.widget.DiffUtil
import com.example.rsschool2021TheCatsApi.network.entities.CatsProperty

object CatsPropertyDiffCallback : DiffUtil.ItemCallback<CatsProperty>() {
    override fun areItemsTheSame(oldItem: CatsProperty, newItem: CatsProperty): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CatsProperty, newItem: CatsProperty): Boolean {
        return oldItem == newItem
    }

    // override fun getChangePayload(oldItem: CatsProperty, newItem: CatsProperty) = Any()
}
