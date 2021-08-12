package com.harish.itest.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.harish.itest.R
import com.harish.itest.databinding.ActivityHomeBinding
import com.harish.itest.ui.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {
  override fun layoutId() = R.layout.activity_home

  override fun viewModelClass() = HomeViewModel::class

  private val imageAdapter by lazy { FlickrImagesAdapter() }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)

    binding.rv.apply {
      layoutManager = LinearLayoutManager(this@HomeActivity)
      adapter = imageAdapter.withLoadStateFooter(FlickrImagesFooterStateAdapter {
        imageAdapter.retry()
      })
    }

    viewModel.images.observe(this, Observer {
      imageAdapter.submitData(lifecycle, it)
    })

    viewModel.searchImages("car")
  }
}