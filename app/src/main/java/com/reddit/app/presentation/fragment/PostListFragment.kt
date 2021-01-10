package com.reddit.app.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.reddit.app.databinding.FragmentPostListBinding
import com.reddit.app.presentation.adapter.PostsAdapter
import com.reddit.app.presentation.viewmodel.PostListViewModel
import com.reddit.app.utils.DividerItemDecorator
import dagger.hilt.android.AndroidEntryPoint

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

            viewModel.redditList.observe(requireActivity(), { postAdapter.submitList(it) })

            mainRecyclerView.adapter = postAdapter
        }
    }


    private fun setSwipeRefreshListener() {
        binding.mainSwipeLayout.setOnRefreshListener { }
    }

    private fun setRecyclerView() {
        linearLayoutManager = binding.mainRecyclerView.layoutManager as LinearLayoutManager
        binding.mainRecyclerView.addItemDecoration(DividerItemDecorator(requireContext()))
        postAdapter = PostsAdapter()
    }
}