package com.vikas.scatteredgriddemo.utils

import android.content.Context
import android.widget.Toast

class AppToast private constructor() {
    fun showToast(context: Context?, message: String?) {
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun showToastLong(context: Context?, message: String?) {
        if (context != null) Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun showToastTesting(context: Context?, message: String?) {
        if (context != null) Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private var instance: AppToast? = null
        fun getInstance(): AppToast? {
            if (instance == null) {
                synchronized(AppToast::class.java) {
                    if (instance == null) {
                        instance =
                            AppToast()
                    }
                }
            }
            return instance
        }
    }
}