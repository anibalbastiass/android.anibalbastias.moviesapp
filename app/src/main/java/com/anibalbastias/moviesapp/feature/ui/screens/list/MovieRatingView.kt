package com.anibalbastias.moviesapp.feature.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.anibalbastias.uikitcompose.components.atom.Caption
import com.anibalbastias.uikitcompose.components.atom.HeadlineH5
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import com.anibalbastias.moviesapp.R
import kotlin.math.roundToInt

@Composable
fun MovieRatingView(voteAverage: Double) {
    ConstraintLayout(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.backgroundColor)
            )
            .width(100.dp)
            .height(100.dp)
            .padding(10.dp)
    ) {
        val (circle, progress, total, text, percentage) = createRefs()
        val value = (voteAverage / 10).toFloat()
        val formattedValue = (voteAverage * 10).roundToInt()

        Box(
            modifier = Modifier
                .constrainAs(circle) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clip(CircleShape)
                .background(colorResource(id = R.color.ratingBackground))
                .size(70.dp)
        )

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

        Caption(
            text = "%",
            color = colorResource(id = R.color.textColor),
            modifier = Modifier
                .constrainAs(percentage) {
                    top.linkTo(text.top)
                    start.linkTo(text.end)
                }
                .padding(top = 5.dp)
        )
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