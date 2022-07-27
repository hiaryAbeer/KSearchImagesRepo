package com.abeerapps.ishowimagesapp.images.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.abeerapps.ishowimagesapp.R
import com.abeerapps.ishowimagesapp.databinding.FragmentSearchImagesBinding
import com.abeerapps.ishowimagesapp.images.ui.viewmodel.ImagesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchImagesFragment : Fragment() {

    private val mViewModel by hiltNavGraphViewModels<ImagesViewModel>(R.id.main_nav_graph)
    private lateinit var mBinding: FragmentSearchImagesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentSearchImagesBinding.inflate(LayoutInflater.from(requireContext()))
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.vm = mViewModel
        mBinding.lifecycleOwner = viewLifecycleOwner

        showSearchError()

        observeSearchField()

        handleSearchResult()
    }

    /**
     * handle Search Result and take actions
     */
    private fun handleSearchResult() {
        lifecycleScope.launch {
            mViewModel.mSearchResult.flowWithLifecycle(lifecycle).collect {
                when (it) {
                    is ImagesViewModel.Actions.NavToSearchList -> {
                        val navigateTo =
                            SearchImagesFragmentDirections.actionSearchImagesFragmentToSearchListFragment()
                        findNavController().navigate(navigateTo)
                    }
                    else -> {

                    }

                }
            }

        }
    }

    /**
     * observe search text to hide errors
     */
    private fun observeSearchField() {
        lifecycleScope.launch {
            mViewModel.mSearchQuery.flowWithLifecycle(lifecycle).collect {
                if (it.isNotEmpty())
                    mViewModel.mShowError.value = false
            }

        }
    }

    /**
     * this method will called when error happen with search
     */
    private fun showSearchError() {
        lifecycleScope.launch {
            mViewModel.mErrorMessage.collect {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}