package com.harish.itest.ui.home

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
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

    /* setup data binding */
    binding.rv.apply {
      layoutManager = LinearLayoutManager(this@HomeActivity)
      adapter = imageAdapter.withLoadStateFooter(FlickrImagesFooterStateAdapter {
        imageAdapter.retry()
      })
    }

    /* observe images */
    viewModel.images.observe(this, {
      imageAdapter.submitData(lifecycle, it)
    })

    /* search trending by default */
    searchImages("Trending")
  }

  /* setup search menu */
  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_home, menu)
    (menu.findItem(R.id.search).actionView as SearchView)
      .apply {
        queryHint = "Search Images..."
      }.setOnQueryTextListener(object : OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
          if (query.isNullOrEmpty()) return false
          searchImages(query)
          return true
        }

        override fun onQueryTextChange(p0: String?): Boolean {
          return false
        }
      })
    return true
  }

  /* search images */
  private fun searchImages(query: String) {
    uiUtils.apply {
      toggleKeyboard(hide = true)
      toast("Searching images for $query")
    }
    viewModel.searchImages(query)
    supportActionBar?.subtitle = query
  }
}