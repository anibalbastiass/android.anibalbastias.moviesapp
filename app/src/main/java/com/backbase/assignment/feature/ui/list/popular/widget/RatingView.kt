package com.backbase.assignment.feature.ui.list.popular.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.backbase.assignment.R
import com.backbase.assignment.databinding.RatingViewBinding
import kotlin.math.roundToInt

class RatingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val POSITIVE_PERCENTAGE: Int = 50
    }

    private var binding: RatingViewBinding =
        RatingViewBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    @SuppressLint("SetTextI18n")
    fun calculate(voteAverage: Double) {
        val formattedValue = (voteAverage * 10).roundToInt()

        binding.run {
            tvPercentage.text = "$formattedValue"

            if (formattedValue > POSITIVE_PERCENTAGE) {
                binding.setProgressValue(TypeProgress.POSITIVE, formattedValue)
            } else {
                binding.setProgressValue(TypeProgress.NEGATIVE, formattedValue)
            }
        }
    }

    private fun RatingViewBinding.setProgressValue(type: TypeProgress, formattedValue: Int) {
        when (type) {
            TypeProgress.POSITIVE -> {
                pbPositiveTotal.visibility = View.VISIBLE
                pbPositiveProgress.visibility = View.VISIBLE
                pbNegativeTotal.visibility = View.GONE
                pbNegativeProgress.visibility = View.GONE

                pbPositiveProgress.progress = formattedValue
            }
            TypeProgress.NEGATIVE -> {
                pbPositiveTotal.visibility = View.GONE
                pbPositiveProgress.visibility = View.GONE
                pbNegativeTotal.visibility = View.VISIBLE
                pbNegativeProgress.visibility = View.VISIBLE

                pbNegativeProgress.progress = formattedValue
            }
        }
    }

    enum class TypeProgress { POSITIVE, NEGATIVE }
}