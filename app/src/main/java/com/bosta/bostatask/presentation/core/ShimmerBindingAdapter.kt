package com.bosta.bostatask.presentation.core

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bosta.bostatask.R
import com.facebook.shimmer.ShimmerFrameLayout


@BindingAdapter("loadShimmerView")
fun ShimmerFrameLayout.loadShimmerView(isLoading: Boolean) {
    visibility = if (isLoading) {
        startShimmer()
        View.VISIBLE
    } else {
        stopShimmer()
        View.GONE
    }
}

fun RecyclerView.attachDivider() {
    addItemDecoration(
        DividerItemDecoration(
            context, DividerItemDecoration.VERTICAL
        ).apply {
            setDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.line_divider
                )!!
            )
        }
    )
}

@SuppressLint("ClickableViewAccessibility")
@BindingAdapter("onDrawableEndClick")
fun EditText.setOnDrawableEndClick(onClick: (() -> Unit)?) {
    if (onClick == null) return
    setOnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP) {
            compoundDrawablesRelative[2]?.let { drawableEnd ->
                val iconStartX = right - drawableEnd.bounds.width() - 40
                if (event.rawX >= iconStartX) {
                    onClick.invoke()
                    return@setOnTouchListener true
                }
            }
        }
        false
    }
}


