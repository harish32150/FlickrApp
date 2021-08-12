package com.harish.itest.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.harish.itest.databinding.ViewFlickrFooterItemBinding
import com.harish.itest.ui.home.FlickrImagesFooterStateAdapter.FlickrImagesFooterVH

class FlickrImagesFooterStateAdapter(private val retryAction: () -> Unit) :
  LoadStateAdapter<FlickrImagesFooterVH>() {

  class FlickrImagesFooterVH(private val binding: ViewFlickrFooterItemBinding) :
    ViewHolder(binding.root) {

    fun bind(loadState: LoadState, retryAction: () -> Unit) {
      binding.btnRetry.setOnClickListener { retryAction.invoke() }
      if (loadState is LoadState.Error)
        binding.textError.text = loadState.error.message

      binding.apply {
        textError.isVisible = loadState !is LoadState.Loading
        progress.isVisible = loadState is LoadState.Loading
        btnRetry.isVisible = loadState !is LoadState.Loading
      }
    }

    companion object {
      fun create(parent: ViewGroup) = LayoutInflater.from(parent.context)
        .let { _inflater -> ViewFlickrFooterItemBinding.inflate(_inflater, parent, false) }
        .let { FlickrImagesFooterVH(it) }
    }
  }

  override fun onBindViewHolder(holder: FlickrImagesFooterVH, loadState: LoadState) {
    holder.bind(loadState, retryAction)
  }

  override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
    FlickrImagesFooterVH.create(parent)
}