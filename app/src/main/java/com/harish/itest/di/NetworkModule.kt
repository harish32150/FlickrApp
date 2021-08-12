package com.harish.itest.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.harish.itest.BuildConfig
import com.harish.itest.network.FlickrService
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
  factory<GsonConverterFactory> {
    GsonConverterFactory.create()
  }
  factory<Gson> {
    GsonBuilder().create()
  }
  factory<RxJava3CallAdapterFactory> {
    RxJava3CallAdapterFactory.create()
  }
  factory {
    providesCache(get())
  }
  factory {
    providesOkhttp(get())
  }
  single {
    providesRetrofit(get(), get(), get())
  }
  single {
    provideFlickrService(get())
  }
}

private fun providesCache(context: Context): Cache {
  val cacheSize = 10 * 1024 * 1024 // 10 MB
  return Cache(context.cacheDir, cacheSize.toLong())
}

private fun providesOkhttp(cache: Cache): OkHttpClient {
  val client = OkHttpClient.Builder()
    .cache(cache)
    .connectTimeout(10, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .readTimeout(10, TimeUnit.SECONDS)

  if (BuildConfig.DEBUG) {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    client.addInterceptor(logging)
  }

  return client.build()
}

private fun providesRetrofit(
  gsonConverterFactory: GsonConverterFactory,
  rxJava2CallAdapterFactory: RxJava3CallAdapterFactory,
  okHttpClient: OkHttpClient
): Retrofit {
  return Retrofit.Builder()
    .baseUrl(BuildConfig.API_BASE)
    .addConverterFactory(gsonConverterFactory)
    .addCallAdapterFactory(rxJava2CallAdapterFactory)
    .client(okHttpClient)
    .build()
}

private fun provideFlickrService(retrofit: Retrofit): FlickrService =
  retrofit.create(FlickrService::class.java)