package com.harish.itest.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.harish.itest.data.FlickrImage
import com.harish.itest.datasource.FlickrImagesDataSource
import com.harish.itest.network.FlickrService
import io.reactivex.rxjava3.core.Flowable

class FlickrRepository(private val flickrService: FlickrService) {

  /**
   * Search images for [query]
   */
  fun searchImages(query: String): Flowable<PagingData<FlickrImage>> =
    Pager(
      config = PagingConfig(
        pageSize = 5,
        enablePlaceholders = false,
      ),
      pagingSourceFactory = { FlickrImagesDataSource(flickrService, query) }
    ).flowable
}