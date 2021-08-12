package com.harish.itest.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

import com.harish.itest.R

@BindingAdapter("i_url")
fun setImageSrc(
  view: ImageView,
  url: String
) {
  Glide
    .with(view.context)
    .load(url)
    .centerCrop()
    .placeholder(R.drawable.ic_baseline_image_24)
    .into(view)
}
