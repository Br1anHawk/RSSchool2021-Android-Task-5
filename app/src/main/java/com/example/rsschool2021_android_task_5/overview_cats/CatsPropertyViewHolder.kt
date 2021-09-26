package com.example.rsschool2021_android_task_5.overview_cats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rsschool2021_android_task_5.R
import com.example.rsschool2021_android_task_5.databinding.CardForCatItemContainerBinding
import com.example.rsschool2021_android_task_5.network.CatsProperty

class CatsPropertyViewHolder private constructor(
    private val binding: CardForCatItemContainerBinding,
    private val listener: RecyclerViewCatImagesListener
): RecyclerView.ViewHolder(binding.root) {
    fun bind(catsProperty: CatsProperty?) {
        if (catsProperty == null) {
            return
        }
        val imgUri = catsProperty.url.toUri().buildUpon().scheme("https").build()
        Glide.with(binding.imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(binding.imageView)

        binding.imageView.setOnClickListener {
            listener.detailedInfoAboutCat(catsProperty)
        }

    }

    companion object {
        fun from(parent: ViewGroup, listener: RecyclerViewCatImagesListener): CatsPropertyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CardForCatItemContainerBinding.inflate(layoutInflater, parent, false)
            return CatsPropertyViewHolder(binding, listener)
        }
    }
}