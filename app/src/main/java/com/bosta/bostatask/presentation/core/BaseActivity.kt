package com.bosta.bostatask.presentation.core

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.internal.ViewUtils.hideKeyboard

abstract class BaseActivity<T : ViewDataBinding> constructor(
    @LayoutRes private val contentLayoutId: Int
) : AppCompatActivity() {


    private lateinit var _binding: T
    protected val binding: T get() = _binding


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this@BaseActivity, contentLayoutId)
        _binding.lifecycleOwner = this
        _binding.executePendingBindings()

        if (_binding.root !is EditText) {
            _binding.root.setOnTouchListener { _, _ ->
                hideKeyboard()
                false
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding.unbind()

    }

    fun hideKeyboard() =
        this.currentFocus?.hideKeyboard()

    private fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputMethodManager.isActive)
            inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)

    }

}
