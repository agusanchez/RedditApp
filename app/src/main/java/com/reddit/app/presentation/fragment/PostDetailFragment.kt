package com.reddit.app.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.reddit.app.R
import com.reddit.app.databinding.FragmentPostDetailBinding
import com.reddit.app.presentation.viewmodel.PostDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : Fragment() {

    private val viewModel: PostDetailViewModel by activityViewModels()
    private lateinit var binding: FragmentPostDetailBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.postDetail.observe(requireActivity(), {
            binding.author.text = it.author
            binding.title.text = it.title

            if (it.thumbnail.isNullOrEmpty() || !URLUtil.isValidUrl(it.thumbnail)) {
                binding.image.visibility = View.GONE
            } else {
                Glide.with(requireContext())
                        .load(it.thumbnail)
                        .transforms(CenterCrop(),
                                RoundedCorners(
                                        requireContext().resources
                                                .getDimensionPixelOffset(R.dimen.ui_1m)
                                )
                        )
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(binding.image)
            }
        })
    }
}