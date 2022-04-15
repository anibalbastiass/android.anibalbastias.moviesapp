package com.backbase.assignment.feature.ui.screens.list

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.anibalbastias.uikitcompose.components.atom.HeadlineH5
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import com.backbase.assignment.R
import kotlin.math.roundToInt

@Composable
fun MovieRatingView(voteAverage: Double) {
    ConstraintLayout(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .padding(20.dp)
    ) {
        val (progress, total, text) = createRefs()
        val value = (voteAverage / 10).toFloat()
        val formattedValue = (voteAverage * 10).roundToInt()

        ProgressView(
            total,
            1f,
            if (value > 0.5)
                R.color.ratingPositiveBackground
            else
                R.color.ratingNegativeBackground
        )

        ProgressView(
            progress,
            (voteAverage / 10).toFloat(),
            if (value > 0.5)
                R.color.ratingPositiveFill
            else
                R.color.ratingNegativeFill
        )

        HeadlineH5(
            text = formattedValue.toString(),
            color = colorResource(id = R.color.textColor),
            modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        // TODO: Add % text
    }
}

@Composable
fun ConstraintLayoutScope.ProgressView(
    scope: ConstrainedLayoutReference,
    value: Float,
    color: Int,
) {
    CircularProgressIndicator(
        color = colorResource(id = color),
        strokeWidth = 6.dp,
        progress = value,
        modifier = Modifier
            .width(60.dp)
            .height(60.dp)
            .constrainAs(scope) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
    )
}

@Preview
@Composable
fun PositiveRatingProgressViewPreview() {
    Surface {
        UIKitComposeTheme {
            MovieRatingView(8.5)
        }
    }
}

@Preview
@Composable
fun NegativeRatingProgressViewPreview() {
    Surface {
        UIKitComposeTheme {
            MovieRatingView(2.4)
        }
    }
}