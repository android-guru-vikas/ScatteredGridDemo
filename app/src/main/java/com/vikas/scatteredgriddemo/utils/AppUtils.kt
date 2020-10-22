package com.vikas.scatteredgriddemo.utils

object AppUtils {

    fun getValueFromData(text: String?): String? {
        return text ?: ""
    }

    @JvmStatic
    fun isNotEmptyNotNull(text: String?): Boolean {
        return text != null && text.trim { it <= ' ' }.isNotEmpty()
    }
}