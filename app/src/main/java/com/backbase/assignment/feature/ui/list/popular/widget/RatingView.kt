package com.backbase.assignment.feature.ui.list.popular.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import com.backbase.assignment.R
import com.backbase.assignment.databinding.RatingViewBinding
import kotlin.math.roundToInt

class RatingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val FULL_PERCENTAGE: Int = 99
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
                pbTotal.setDrawable(R.drawable.rating_positive_full)
                pbCurrentProgress.setDrawable(R.drawable.rating_positive_progress)
            } else {
                pbTotal.setDrawable(R.drawable.rating_negative_full)
                pbCurrentProgress.setDrawable(R.drawable.rating_negative_progress)
            }

            pbTotal.progress = FULL_PERCENTAGE
            pbCurrentProgress.progress = formattedValue
        }
    }

    private fun ProgressBar.setDrawable(@DrawableRes drawable: Int) {
        progressDrawable = AppCompatResources.getDrawable(context, drawable)
    }
}