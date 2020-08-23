package com.vikas.scatteredgriddemo.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vikas.scatteredgriddemo.utils.Constants

class LiciousViewModel(state: SavedStateHandle) : ViewModel() {

    private val savedStateHandle = state

    fun saveFoodItems(foodItems: String?) {
        savedStateHandle.set(Constants.KEY_FOOD_ITEMS, foodItems)
    }

    fun getFoodItems(): LiveData<String?>? {
        return savedStateHandle?.getLiveData<String?>(Constants.KEY_FOOD_ITEMS)
    }
}