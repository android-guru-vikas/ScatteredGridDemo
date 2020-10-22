package com.tamilsouthnews.backend

import android.os.Handler
import com.vikas.scatteredgriddemo.backend.APIHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * Created by Vikas on 13/01/20.
 */
abstract class RetryableCallback<T> internal constructor(private val call: Call<T?>?, totalRetries: Int) : Callback<T?> {
    private var totalRetries = 10
    private var retryCount = 0
    override fun onResponse(call: Call<T?>, response: Response<T?>) {
        if (!APIHelper.isCallSuccess(response)) if (retryCount++ < totalRetries) {
            Timber.d("Retrying API Call -  ($retryCount / $totalRetries)")
            retry()
        } else onFinalResponse(call, response) else onFinalResponse(call, response)
    }

    override fun onFailure(call: Call<T?>?, t: Throwable?) {
        Timber.e(t?.message)
        if (retryCount++ < totalRetries) {
            Timber.e("Retrying API Call -  ($retryCount / $totalRetries)")
            retry()
        } else onFinalFailure(call, t)
    }

    open fun onFinalResponse(call: Call<T?>?, response: Response<T?>?) {}
    open fun onFinalFailure(call: Call<T?>?, t: Throwable?) {}
    private fun retry() {
        val expDelay = (RETRY_DELAY * Math.pow(2.0, Math.max(0, retryCount - 1).toDouble())).toInt()
        Timber.d("Retrying API Call -  ($retryCount / $totalRetries), Delay : $expDelay")
        Handler().postDelayed({ call?.clone()?.enqueue(this) }, expDelay.toLong())
    }

    companion object {
        private const val RETRY_DELAY = 5000.0
    }

    init {
        this.totalRetries = totalRetries
    }
}