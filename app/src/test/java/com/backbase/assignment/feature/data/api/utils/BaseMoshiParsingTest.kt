package com.backbase.assignment.feature.data.api.utils

import androidx.annotation.CallSuper
import com.squareup.moshi.Moshi

open class BaseMoshiParsingTest {

    protected lateinit var moshi: Moshi

    @CallSuper
    open fun setup() {
        moshi = Moshi.Builder().build()
    }
}