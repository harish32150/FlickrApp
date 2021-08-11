package com.harish.itest.ui.home

import com.harish.itest.R
import com.harish.itest.databinding.ActivityHomeBinding
import com.harish.itest.ui.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {
  override fun layoutId() = R.layout.activity_home

  override fun viewModelClass() = HomeViewModel::class
}