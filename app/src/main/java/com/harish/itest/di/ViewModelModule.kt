package com.harish.itest.di

import com.harish.itest.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { HomeViewModel(get()) }
}