package com.bosta.bostatask.presentation.core

import android.os.Bundle

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> constructor(
    @LayoutRes private val contentLayoutId: Int
) : AppCompatActivity() {


    private lateinit var _binding: T
    protected val binding: T get() = _binding

    protected inline fun binding(block: T.() -> Unit): T = binding.apply(block)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this@BaseActivity, contentLayoutId)
        _binding.lifecycleOwner = this
        _binding.executePendingBindings()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding.unbind()

    }


}
