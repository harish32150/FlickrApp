package com.harish.itest.network.response

import com.google.gson.annotations.SerializedName
import com.harish.itest.data.FlickrImage

data class FlickrSearchImagesResponse(
  @SerializedName("photos") val photosData: FlickSearchImagesResponseData,
  @SerializedName("stat") val stat: String
)

data class FlickSearchImagesResponseData(
  @SerializedName("page") val page: Int,
  @SerializedName("pages") val totalPages: Int,
  @SerializedName("perpage") val perPage: Int,
  @SerializedName("total") val total: Int,
  @SerializedName("photo") val photos: List<FlickrImage>
)