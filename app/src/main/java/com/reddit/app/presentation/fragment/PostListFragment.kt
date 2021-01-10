package com.reddit.app.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reddit.app.databinding.FragmentPostListBinding
import com.reddit.app.presentation.adapter.PostsAdapter
import com.reddit.app.presentation.viewmodel.PostListViewModel
import com.reddit.app.utils.DividerItemDecorator
import com.reddit.app.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PostListFragment: Fragment() {

    private val viewModel: PostListViewModel by activityViewModels()
    private lateinit var binding: FragmentPostListBinding
    private lateinit var postAdapter: PostsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentPostListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            setRecyclerView()
            setSwipeRefreshListener()
            setOnScrollListener()

            lifecycleScope.launchWhenCreated { viewModel.spinner.collect { progress.visible = it } }
            viewModel.redditList.observe(requireActivity(), { postAdapter.submitList(it) })
            viewModel.isRefreshing.observe(requireActivity(), { mainSwipeLayout.isRefreshing = it })

            mainRecyclerView.adapter = postAdapter
        }
    }


    private fun setSwipeRefreshListener() {
        binding.mainSwipeLayout.setOnRefreshListener { viewModel.refreshForNewPosts() }
    }

    private fun setRecyclerView() {
        linearLayoutManager = binding.mainRecyclerView.layoutManager as LinearLayoutManager
        binding.mainRecyclerView.addItemDecoration(DividerItemDecorator(requireContext()))
        postAdapter = PostsAdapter()
    }

    private fun setOnScrollListener() {
        binding.mainRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                viewModel.lastVisible.value = linearLayoutManager.findLastVisibleItemPosition()
            }
        })
    }
}