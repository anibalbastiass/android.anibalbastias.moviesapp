package com.backbase.assignment.feature.ui.list.popular.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.backbase.assignment.databinding.RatingViewBinding

class RatingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: RatingViewBinding =
        RatingViewBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun calculate() {

    }
}