package com.abeerapps.ishowimagesapp.images.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.abeerapps.ishowimagesapp.R
import com.abeerapps.ishowimagesapp.databinding.FragmentSearchListBinding
import com.abeerapps.ishowimagesapp.images.ui.viewmodel.ImagesViewModel
import kotlinx.coroutines.launch

class SearchListFragment : Fragment() {

    private val mViewModel by hiltNavGraphViewModels<ImagesViewModel>(R.id.main_nav_graph)
    private lateinit var mBinding: FragmentSearchListBinding
    private lateinit var mAdapter: ImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentSearchListBinding.inflate(LayoutInflater.from(requireContext()))
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.searchQuery.text = mViewModel.mSearchQuery.value

        mAdapter = ImagesAdapter(mViewModel.mImagesList.value.results, mViewModel)
        mBinding.rvSearchListFragment.layoutManager = GridLayoutManager(requireContext(), 2)
        mBinding.rvSearchListFragment.adapter = mAdapter

        lifecycleScope.launch {
            mViewModel.mSearchResult.collect {
                if (it is ImagesViewModel.Actions.NavToPhotographerPortfolio) {
                    val uri: Uri =
                        Uri.parse(it.link) // missing 'http://' will cause crashed
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    requireContext().startActivity(intent)
                }
            }
        }
    }
}