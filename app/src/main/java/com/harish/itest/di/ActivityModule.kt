package com.harish.itest.di

import androidx.appcompat.app.AppCompatActivity
import com.harish.itest.utils.UIUtils
import org.koin.dsl.module

val activityModule = module {
  factory { (activity: AppCompatActivity) ->
    UIUtils(activity)
  }
}