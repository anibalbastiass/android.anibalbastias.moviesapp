@file:JvmName("LiveDataHelper")
package com.backbase.assignment.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@Throws(InterruptedException::class)
inline fun <reified T: Any> LiveData<T>.getFirst(): T {
    val retVal = arrayOfNulls<T>(1)
    val latch = CountDownLatch(1)

    val observer = object : Observer<T> {
        override fun onChanged(result: T?) {
            retVal[0] = result
            latch.countDown()
            removeObserver(this)
        }
    }
    observeForever(observer)
    latch.await(1, TimeUnit.SECONDS)
    return retVal[0] as T
}