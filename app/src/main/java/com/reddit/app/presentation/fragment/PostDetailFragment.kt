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
import com.google.android.material.snackbar.Snackbar
import com.reddit.app.R
import com.reddit.app.databinding.FragmentPostDetailBinding
import com.reddit.app.presentation.viewmodel.PostDetailViewModel
import com.reddit.app.utils.PermissionRequester
import com.reddit.app.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : Fragment() {

    private val viewModel: PostDetailViewModel by activityViewModels()
    private lateinit var binding: FragmentPostDetailBinding
    private lateinit var permissionRequester: PermissionRequester

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        permissionRequester = PermissionRequester(requireActivity())

        viewModel.pictureSavedStatus.observe(requireActivity(), { status -> showPictureMessage(status) })
        viewModel.postDetail.observe(requireActivity(), { post ->
            binding.savePicture.visible = true
            binding.author.text = post.author
            binding.title.text = post.title

            loadThumbnail(post.thumbnail)
            setSavePictureListener(post.thumbnail)
        })
    }

    private fun setSavePictureListener(thumbnail: String?) {
        binding.savePicture.setOnClickListener {
            permissionRequester.request {
                thumbnail?.let { viewModel.savePictureOnGallery(it) }
            }
        }
    }

    private fun loadThumbnail(thumbnail: String?) {
        if (thumbnail.isNullOrEmpty() || !URLUtil.isValidUrl(thumbnail)) {
            binding.image.visibility = View.GONE
        } else {
            Glide.with(requireContext())
                    .load(thumbnail)
                    .transforms(CenterCrop(),
                            RoundedCorners(
                                    requireContext().resources
                                            .getDimensionPixelOffset(R.dimen.ui_1m)
                            )
                    )
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.image)
        }
    }

    private fun showPictureMessage(status: Boolean) {
        val message = if (status) {
            requireContext().resources.getString(R.string.post_detail_picture_successfully_saved)
        } else {
            requireContext().resources.getString(R.string.post_detail_picture_successfully_saved)
        }
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }
}