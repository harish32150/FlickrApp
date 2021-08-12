package com.harish.itest.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.harish.itest.data.FlickrImage
import com.harish.itest.databinding.ViewFlickImageItemBinding
import com.harish.itest.ui.home.FlickrImagesAdapter.FlickrImageVH

class FlickrImagesAdapter : PagingDataAdapter<FlickrImage, FlickrImageVH>(DIFF_CALLBACK) {

  class FlickrImageVH(val binding: ViewFlickImageItemBinding) : ViewHolder(binding.root) {
    fun bind(flickrImage: FlickrImage) {
      binding.image = flickrImage
    }

    companion object {
      fun create(parent: ViewGroup) = LayoutInflater.from(parent.context)
        .let { _inflater -> ViewFlickImageItemBinding.inflate(_inflater, parent, false) }
        .let { FlickrImageVH(it) }
    }
  }

  override fun onBindViewHolder(holder: FlickrImageVH, position: Int) {
    getItem(position)?.let { holder.bind(it) }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FlickrImageVH.create(parent)

  companion object {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FlickrImage>() {
      override fun areItemsTheSame(oldItem: FlickrImage, newItem: FlickrImage) =
        oldItem.id == newItem.id

      override fun areContentsTheSame(oldItem: FlickrImage, newItem: FlickrImage) =
        oldItem == newItem
    }
  }
}