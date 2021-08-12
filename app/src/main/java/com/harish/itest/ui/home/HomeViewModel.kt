package com.harish.itest.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.harish.itest.data.FlickrImage
import com.harish.itest.repository.FlickrRepository
import com.harish.itest.ui.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class HomeViewModel(private val flickrRepository: FlickrRepository) : BaseViewModel() {

  val images = MutableLiveData<PagingData<FlickrImage>>()

  /**
   * Search images
   */
  fun searchImages(query: String) {
    flickrRepository.searchImages(query)
      .cachedIn(viewModelScope)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe {
        images.postValue(it)
      }.queue()
  }
}