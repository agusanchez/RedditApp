package com.reddit.app.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reddit.app.R
import com.reddit.app.databinding.FragmentPostListBinding
import com.reddit.app.presentation.adapter.PostsAdapter
import com.reddit.app.presentation.viewholder.PostActionListener
import com.reddit.app.presentation.viewmodel.PostListViewModel
import com.reddit.app.utils.DividerItemDecorator
import com.reddit.app.utils.collectFlow
import com.reddit.app.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.view_empty_state_view.*
import kotlinx.android.synthetic.main.view_network_error_view.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PostListFragment: Fragment(), PostActionListener {

    private val viewModel: PostListViewModel by activityViewModels()
    private lateinit var binding: FragmentPostListBinding
    private lateinit var postAdapter: PostsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
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
            viewModel.viewModelScope.collectFlow(viewModel.showEmptyView) { viewEmptyList.visible = it }
            viewModel.viewModelScope.collectFlow(viewModel.showNetworkError) { viewNetworkError.visible = it }
            viewModel.viewModelScope.collectFlow(viewModel.showPostListView) { mainRecyclerView.visible = it }

            mainRecyclerView.adapter = postAdapter
        }
    }

    private fun setSwipeRefreshListener() {
        binding.mainSwipeLayout.setOnRefreshListener { viewModel.refreshForNewPosts() }
    }

    private fun setRecyclerView() {
        linearLayoutManager = binding.mainRecyclerView.layoutManager as LinearLayoutManager
        binding.mainRecyclerView.addItemDecoration(DividerItemDecorator(requireContext()))
        postAdapter = PostsAdapter(postActionListener = this)
    }

    private fun setOnScrollListener() {
        binding.mainRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                viewModel.lastVisible.value = linearLayoutManager.findLastVisibleItemPosition()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dismiss_all_posts_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_dismiss_all) {
            viewModel.dismissAllPosts(postAdapter.currentList)
            postAdapter.submitList(emptyList())
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDismissedPost(id: String) {
        viewModel.dismissPost(id)
    }

    override fun onPostClicked(id: String) {
        viewModel.postClicked(id)
    }

    override fun onThumbnailClicked(thumbnailUrl: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(thumbnailUrl)))
    }
}