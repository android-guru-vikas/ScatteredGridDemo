package com.vikas.scatteredgriddemo.utils

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.math.BigDecimal
import java.math.RoundingMode

object AppUtils {
    fun loadSettingsJsonFile(context: Context?): String? {
        var br: BufferedReader? = null
        val sb = StringBuffer()
        try {
            br = BufferedReader(
                InputStreamReader(
                    context?.assets?.open(
                        "product_reorder.json"
                    ), "UTF-8"
                )
            )
            var line: String?
            if (br != null) {
                while (br.readLine().also { line = it } != null) {
                    sb.append(line)
                }
            }
        } catch (ioe: IOException) {
            return null
        } catch (e: Exception) {
        } finally {
            try {
                br?.close()
            } catch (e: IOException) {
            }
        }
        return sb.toString()
    }

    fun roundToTwoDigit(value: Double): String? {
        var bd = BigDecimal.valueOf(value)
        bd = bd.setScale(2, RoundingMode.HALF_UP)
        return bd.toPlainString()
    }

}