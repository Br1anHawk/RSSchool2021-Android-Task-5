package com.example.rsschool2021_android_task_5.overview_cats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rsschool2021_android_task_5.R
import com.example.rsschool2021_android_task_5.databinding.FragmentDetailedCatInfoBinding

class DetailedCatInfoFragment : Fragment() {
    private var _binding: FragmentDetailedCatInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedCatInfoBinding.inflate(inflater, container, false)

        val args: DetailedCatInfoFragmentArgs by navArgs()
        val catsProperty = args.catsProperty
        Glide.with(binding.imageView.context)
            .load(catsProperty.url.toUri().buildUpon().scheme("https").build())
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(binding.imageView)

        with(binding) {
            if (catsProperty.breeds.isNotEmpty()) {
                val breed = catsProperty.breeds[0]
                textViewCatName.text = "${breed.name}\n(\"${breed.alt_names}\")"
                textViewCatTemperament.text = breed.temperament
                textViewCatOrigin.text = breed.origin
                textViewCatLifeSpan.text = breed.life_span
                textViewCatDescription.text = breed.description

            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}