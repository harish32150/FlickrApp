package com.harish.itest.network

import com.harish.itest.network.response.FlickrSearchImagesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {
  @GET("/services/rest/")
  fun serachImages(
    @Query("method") method: String = "flickr.photos.search",
    @Query("api_key") apiKey: String = "062a6c0c49e4de1d78497d13a7dbb360",
    @Query("format") format: String = "json",
    @Query("nojsoncallback") nojsonCallback: Int = 1,
    @Query("extras") extraParams: String = "owner_name,url_m",
    @Query("per_page") perPage: Int = 5,
    @Query("page") page: Int = 1,
    @Query("tags") searchTags: String
  ): Single<FlickrSearchImagesResponse>
}