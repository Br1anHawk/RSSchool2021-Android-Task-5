package com.example.rsschool2021_android_task_5.overview_cats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rsschool2021_android_task_5.R
import com.example.rsschool2021_android_task_5.SPAN_COUNT_FOR_GRID_LAYOUT_MANAGER
import com.example.rsschool2021_android_task_5.databinding.FragmentRecyclerViewCatImagesBinding
import com.example.rsschool2021_android_task_5.network.CatsApiStatus
import com.example.rsschool2021_android_task_5.network.CatsProperty
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RecyclerViewCatImagesFragment : Fragment(), RecyclerViewCatImagesListener {
    private var _binding: FragmentRecyclerViewCatImagesBinding? = null
    private val binding get() = _binding!!

    private val recyclerViewCatImagesViewModel: RecyclerViewCatImagesViewModel by lazy {
        ViewModelProvider(this)[RecyclerViewCatImagesViewModel::class.java]
    }

    private val recyclerViewCatImagesAdapter by lazy(LazyThreadSafetyMode.NONE) {
        RecyclerViewCatImagesAdapter(this)
    }

    private val _lifecycleCoroutineScopeJob by lazy {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                recyclerViewCatImagesViewModel.data.collectLatest {
                    recyclerViewCatImagesAdapter.submitData(it)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _lifecycleCoroutineScopeJob.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerViewCatImagesBinding.inflate(inflater, container, false)

        binding.recyclerViewCatImages.apply {
            layoutManager = GridLayoutManager(context, SPAN_COUNT_FOR_GRID_LAYOUT_MANAGER)
            adapter = recyclerViewCatImagesAdapter
        }

        recyclerViewCatImagesViewModel.status.observe(viewLifecycleOwner, {
            it.let {
                binding.imageViewStatusConnection.visibility = View.VISIBLE
                when(it) {
                    CatsApiStatus.LOADING -> binding.imageViewStatusConnection.setImageResource(R.drawable.loading_img)
                    CatsApiStatus.ERROR -> binding.imageViewStatusConnection.setImageResource(R.drawable.ic_connection_error)
                    CatsApiStatus.DONE -> binding.imageViewStatusConnection.visibility = View.GONE
                }
            }
        })

//        recyclerViewCatImagesViewModel.properties.observe(viewLifecycleOwner, {
//            it?.let {
//                recyclerViewCatImagesAdapter.submitList(it)
//            }
//        })

        return binding.root
    }

    override fun detailedInfoAboutCat(catsProperty: CatsProperty) {
        this.findNavController().navigate(RecyclerViewCatImagesFragmentDirections.actionRecyclerViewCatImagesFragmentToDetailedCatInfoFragment(catsProperty))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _lifecycleCoroutineScopeJob.cancel()
    }
}