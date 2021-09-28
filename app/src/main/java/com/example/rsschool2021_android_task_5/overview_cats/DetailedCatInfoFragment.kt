package com.example.rsschool2021_android_task_5.overview_cats

import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
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
import java.io.File
import java.io.FileOutputStream
import android.graphics.Bitmap

import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import java.lang.Exception


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

        binding.buttonSaveImageOnDevice.setOnClickListener {
            var fos: FileOutputStream? = null
             try {
                //val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                val imagesDir = Environment.getStorageDirectory()
                //imagesDir.mkdir()
                val image = File(imagesDir, catsProperty.id + ".jpg")
                fos = FileOutputStream(image)
                val bitmap = binding.imageView.drawable.toBitmap(100, 100)
                fos.use {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                }
            } catch (e: Exception) {
                Log.e("SAVE-IMAGE", e.stackTraceToString())
            } finally {
                fos?.flush()
                fos?.close()
            }
        }

        with(binding) {
            if (catsProperty.breeds.isNotEmpty()) {
                val breed = catsProperty.breeds[0]
                textViewCatName.text = breed.name
                if (breed.alt_names.isEmpty()) {
                    textViewCatAltName.visibility = View.GONE
                } else {
                    textViewCatAltName.text = "\"${breed.alt_names}\""
                }
                textViewCatTemperament.text = breed.temperament
                textViewCatOrigin.text = breed.origin
                textViewCatLifeSpan.text = breed.life_span
                textViewCatDescription.text = breed.description
                ratingBarCatAdaptability.rating = breed.adaptability.toFloat()

            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}