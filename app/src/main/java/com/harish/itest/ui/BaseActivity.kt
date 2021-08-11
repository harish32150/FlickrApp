package com.harish.itest.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.harish.itest.BR
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
  /** activity layout binding */
  protected lateinit var binding: B

  /** view model */
  protected open val viewModel: VM by lazy { getViewModel(clazz = viewModelClass()) }

  /** provide binding layout resource id */
  @LayoutRes
  protected abstract fun layoutId(): Int

  /** provide viewModel class of type VM */
  protected abstract fun viewModelClass(): KClass<VM>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, layoutId())
    binding.apply {
      lifecycleOwner = this@BaseActivity
      setVariable(BR.viewModel, viewModel)
    }
  }
}