package com.vikas.scatteredgriddemo.backend

import com.tamilsouthnews.backend.RetryableCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * Created by Vikas on 16/05/16.
 */
object APIHelper {
    const val DEFAULT_RETRIES = 100
    @JvmStatic
    fun <T> enqueueWithRetry(call: Call<T?>?, retryCount: Int, callback: Callback<T?>?) {
        call?.enqueue(object : RetryableCallback<T?>(call, retryCount) {
            override fun onFinalResponse(call: Call<T?>?, response: Response<T?>?) {
                Timber.d("Inside onFinalResponse RetryCount : " + retryCount + ", response code is : " + response?.code())
                callback?.onResponse(call, response)
            }

            override fun onFinalFailure(call: Call<T?>?, t: Throwable?) {
                Timber.e("Inside onFinalFailure RetryCount : " + retryCount + ", error : " + t?.message)
                callback?.onFailure(call, t)
            }
        })
    }

    fun <T> enqueueWithRetry(call: Call<T?>?, callback: Callback<T?>?) {
        enqueueWithRetry(call, DEFAULT_RETRIES, callback)
    }

    fun isCallSuccess(response: Response<*>?): Boolean {
        val code: Int? = response?.code()
        return code in 200..399
    }
}