package com.harish.itest.datasource

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.harish.itest.data.FlickrImage
import com.harish.itest.network.FlickrService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class FlickrImagesDataSource(private val flickrService: FlickrService, private val query: String) :
  RxPagingSource<Int, FlickrImage>() {
  override fun getRefreshKey(state: PagingState<Int, FlickrImage>): Int? {
//    when {
//      state.anchorPosition == null -> null
//      state.closestPageToPosition(state.anchorPosition!!) == null -> null
//
//    }
//    state.anchorPosition.let { _anchorPos ->
//      if(_anchorPos == null) return null
//      state.closestItemToPosition(_anchorPos).let { _anchorPage ->
//        if(_anchorPage == null) return null
//        _anchorPage.
//      }
//    }
    TODO("not implemented")
  }

  override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, FlickrImage>> {
    val pos = params.key ?: 1
    return flickrService.searchImages(searchTags = query, page = pos, perPage = params.loadSize)
      .subscribeOn(Schedulers.io())
      .map { it.photosData }
      .map {
        LoadResult.Page(
          data = it.photos,
          prevKey = if (pos == 1) null else pos - 1,
          nextKey = if (pos == it.totalPages) null else pos + 1
        ) as LoadResult<Int, FlickrImage>
      }.onErrorReturn { LoadResult.Error(it) }
  }
}