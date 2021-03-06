package com.example.rsschool2021TheCatsApi.overviewCats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rsschool2021TheCatsApi.R
import com.example.rsschool2021TheCatsApi.SPAN_COUNT_FOR_GRID_LAYOUT_MANAGER
import com.example.rsschool2021TheCatsApi.databinding.FragmentRecyclerViewCatImagesBinding
import com.example.rsschool2021TheCatsApi.network.entities.CatsProperty
import com.example.rsschool2021TheCatsApi.pagination.CatsLoaderStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class RecyclerViewCatImagesFragment : Fragment(), RecyclerViewCatImagesListener {
    private var _binding: FragmentRecyclerViewCatImagesBinding? = null
    private val binding get() = _binding!!

    private val recyclerViewCatImagesViewModel: RecyclerViewCatImagesViewModel by lazy {
        ViewModelProvider(this)[RecyclerViewCatImagesViewModel::class.java]
    }

    private val recyclerViewCatImagesAdapter by lazy(LazyThreadSafetyMode.NONE) {
        RecyclerViewCatImagesAdapter(this)
    }

//    private val _lifecycleCoroutineScopeJob by lazy {
//        lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
//                recyclerViewCatImagesViewModel.dataFlowCatsProperty.collectLatest {
//                    recyclerViewCatImagesAdapter.submitData(it)
//                }
//            }
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // _lifecycleCoroutineScopeJob.start()

        _binding = FragmentRecyclerViewCatImagesBinding.inflate(inflater, container, false)

        binding.recyclerViewCatImages.apply {
            layoutManager = GridLayoutManager(context, SPAN_COUNT_FOR_GRID_LAYOUT_MANAGER)
            adapter = recyclerViewCatImagesAdapter.withLoadStateHeaderAndFooter(
                header = CatsLoaderStateAdapter(recyclerViewCatImagesAdapter),
                footer = CatsLoaderStateAdapter(recyclerViewCatImagesAdapter)
            )
        }

        recyclerViewCatImagesAdapter.addLoadStateListener { state ->
            with(binding) {
                recyclerViewCatImages.isVisible = state.refresh != LoadState.Loading
                progressBarStatusConnection.isVisible = state.refresh == LoadState.Loading
                containerForErrorConnection.isVisible = state.refresh is LoadState.Error
            }
        }

        binding.buttonRetryConnection.setOnClickListener {
            recyclerViewCatImagesAdapter.retry()
            recyclerViewCatImagesAdapter.refresh()
            // TODO: Doesn't work, connection retry.
        }

//        recyclerViewCatImagesViewModel.status.observe(viewLifecycleOwner, {
//            it.let {
//                binding.imageViewStatusConnection.visibility = View.VISIBLE
//                when(it) {
//                    CatsApiStatus.LOADING -> binding.imageViewStatusConnection.setImageResource(R.drawable.loading_img)
//                    CatsApiStatus.ERROR -> binding.imageViewStatusConnection.setImageResource(R.drawable.ic_connection_error)
//                    CatsApiStatus.DONE -> binding.imageViewStatusConnection.visibility = View.GONE
//                }
//            }
//        })

//        recyclerViewCatImagesViewModel.properties.observe(viewLifecycleOwner, {
//            it?.let {
//                recyclerViewCatImagesAdapter.submitList(it)
//            }
//        })

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                recyclerViewCatImagesViewModel.dataFlowCatsProperty.collectLatest {
                    recyclerViewCatImagesAdapter.submitData(it)
                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            AlertDialog.Builder(requireContext()).apply {
                setTitle(getString(R.string.alert_dialog_text_confirmation))
                setMessage(getString(R.string.alert_dialog_text_question))

                setPositiveButton(getString(R.string.alert_dialog_text_yes)) { _, _ ->
                    requireActivity().finishAndRemoveTask()
                }

                setNegativeButton(getString(R.string.alert_dialog_text_no)) { _, _ ->
                }
                setCancelable(true)
            }.create().show()
        }

        return binding.root
    }

    override fun detailedInfoAboutCat(catsProperty: CatsProperty) {
        try {
            this
                .findNavController()
                .navigate(
                    RecyclerViewCatImagesFragmentDirections
                        .actionRecyclerViewCatImagesFragmentToDetailedCatInfoFragment(catsProperty)
                )
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        // _lifecycleCoroutineScopeJob.cancel()
    }
}
