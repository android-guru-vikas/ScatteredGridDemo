package com.vikas.scatteredgriddemo.model

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.vikas.scatteredgriddemo.LiciousDemoApplication
import com.vikas.scatteredgriddemo.utils.AppUtils
import timber.log.Timber

class LiciousViewModel constructor(application: Application) : AndroidViewModel(application) {

    private var modelLiveData: MutableLiveData<FoodItems?>? = null


    fun loadItems(jsonString: String?): LiveData<FoodItems?>? {
        modelLiveData = MutableLiveData<FoodItems?>()
        if (!TextUtils.isEmpty(jsonString)) {
            val gson = Gson()
            val foodItems = gson.fromJson<FoodItems>(jsonString, FoodItems::class.java)
            modelLiveData?.value = foodItems
        }
        return modelLiveData
    }
}