package com.example.rsschool2021TheCatsApi.overviewCats

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.rsschool2021TheCatsApi.R
import com.example.rsschool2021TheCatsApi.databinding.FragmentDetailedCatInfoBinding
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
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
                    .error(R.drawable.ic_cat_broken_image)
            )
            .listener(
                object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        try {
                            with(binding) {
                                if (
                                    File(
                                        "${Environment
                                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)}",
                                        "${catsProperty.id}$IMAGE_FILE_EXTENSION"
                                    ).exists()
                                ) {
                                    buttonSaveImageOnDevice.setImageResource(R.drawable.ic_save_on_device_ok)
                                    return false
                                }
                                buttonSaveImageOnDevice.setImageResource(R.drawable.ic_save_on_device_image)
                                buttonSaveImageOnDevice.isEnabled = true
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        return false
                    }
                }
            )
            .into(binding.imageView)

        binding.buttonSaveImageOnDevice.isEnabled = false
        binding.buttonSaveImageOnDevice.setOnClickListener {
            binding.buttonSaveImageOnDevice.setImageResource(R.drawable.loading_animation)
            requestPermissions(
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                REQUEST_CODE
            )

            val imageFileName = "${catsProperty.id}$IMAGE_FILE_EXTENSION"
            var fos: OutputStream? = null
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val resolver = binding.imageView.context.contentResolver
                    val contentValues = ContentValues()
                    contentValues.put(
                        MediaStore.MediaColumns.DISPLAY_NAME,
                        imageFileName
                    )
                    contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    contentValues.put(
                        MediaStore.MediaColumns.RELATIVE_PATH,
                        Environment.DIRECTORY_PICTURES
                    )
                    val imageUri =
                        resolver?.insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            contentValues
                        )
                    fos = resolver?.openOutputStream(requireNotNull(imageUri))
                } else {
                    val imagesDir =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                            .toString()
                    val image = File(imagesDir, imageFileName)
                    fos = FileOutputStream(image)
                }
                binding
                    .imageView
                    .drawable
                    .toBitmap()
                    .compress(Bitmap.CompressFormat.JPEG, QUALITY, fos)

                val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                val picturesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                val contentUri = Uri.fromFile(picturesDirectory)
                mediaScanIntent.data = contentUri
                context?.sendBroadcast(mediaScanIntent)

                binding.buttonSaveImageOnDevice.setImageResource(R.drawable.ic_save_on_device_ok)
            } catch (e: Exception) {
                e.printStackTrace()
                binding.buttonSaveImageOnDevice.setImageResource(R.drawable.ic_save_on_device_error)
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

                ratingBarCatAffectionLevel.rating = breed.affection_level.toFloat()
                ratingBarCatChildFriendly.rating = breed.child_friendly.toFloat()
                ratingBarCatDogFriendly.rating = breed.dog_friendly.toFloat()
                ratingBarCatEnergyLevel.rating = breed.energy_level.toFloat()
                ratingBarCatGrooming.rating = breed.grooming.toFloat()
                ratingBarCatHealthIssues.rating = breed.health_issues.toFloat()
                ratingBarCatIntelligence.rating = breed.intelligence.toFloat()
                ratingBarCatSheddingLevel.rating = breed.shedding_level.toFloat()
                ratingBarCatSocialNeeds.rating = breed.social_needs.toFloat()
                ratingBarCatStrangerFriendly.rating = breed.stranger_friendly.toFloat()
                ratingBarCatVocalisation.rating = breed.vocalisation.toFloat()
                ratingBarCatExperimental.rating = breed.experimental.toFloat()
                ratingBarCatHairless.rating = breed.hairless.toFloat()
                ratingBarCatNatural.rating = breed.natural.toFloat()
                ratingBarCatRare.rating = breed.rare.toFloat()
                ratingBarCatRex.rating = breed.rex.toFloat()
                ratingBarCatSuppressedTail.rating = breed.suppressed_tail.toFloat()
                ratingBarCatShortLegs.rating = breed.short_legs.toFloat()

                imageViewCatWikipediaInfo.setOnClickListener {
                    val implicitInternetWebSiteOpenIntent = Intent(Intent.ACTION_VIEW)
                    implicitInternetWebSiteOpenIntent.data = Uri.parse(breed.wikipedia_url)
                    startActivity(implicitInternetWebSiteOpenIntent)
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val IMAGE_FILE_EXTENSION = ".jpg"
        const val REQUEST_CODE = 111
        const val QUALITY = 100
    }
}
