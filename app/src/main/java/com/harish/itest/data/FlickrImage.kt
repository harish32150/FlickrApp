package com.harish.itest.data

import com.google.gson.annotations.SerializedName

data class FlickrImage(
  @SerializedName("id") val id: String,
  @SerializedName("owner") val owner: String,
  @SerializedName("secret") val secret: String,
  @SerializedName("server") val server: String,
  @SerializedName("farm") val farm: Int,
  @SerializedName("title") val title: String,
  @SerializedName("ownername") val ownerName: String,
  @SerializedName("url_m") val urlMedium: String?,
  @SerializedName("height_m") val height: Int,
  @SerializedName("width_m") val width: Int
) {
  fun imageUrl() = "https://farm$farm.staticflickr.com/$server/${id}_${secret}_m.jpg"
}