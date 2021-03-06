package com.harish.itest

import android.app.Application
import com.harish.itest.di.activityModule
import com.harish.itest.di.networkModule
import com.harish.itest.di.repositoryModule
import com.harish.itest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FlickrApp : Application() {
  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidLogger()
      androidContext(this@FlickrApp)
      modules(
        networkModule,
        repositoryModule,
        viewModelModule,
        activityModule
      )
    }
  }
}