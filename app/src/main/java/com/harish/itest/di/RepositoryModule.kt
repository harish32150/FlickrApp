package com.harish.itest.di

import com.harish.itest.repository.FlickrRepository
import org.koin.dsl.module

val repositoryModule = module {
  single { FlickrRepository(get()) }
}