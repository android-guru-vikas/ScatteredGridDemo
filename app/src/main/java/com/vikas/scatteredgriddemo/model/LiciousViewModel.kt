package com.vikas.scatteredgriddemo.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vikas.scatteredgriddemo.model.FoodItems

class LiciousViewModel constructor(application: Application) : AndroidViewModel(application) {

    private var bookingDetailsLiveData: MutableLiveData<FoodItems?>? = null

    fun getBookingDetails(bookingId: String?): LiveData<FoodItems?>? {
//        bookingDetailsLiveData = MutableLiveData<FoodItems?>()
//        val networkService: NetworkService? = NetworkServiceBuilder.buildMain()
//        networkService?.getBookingDetails(bookingId)
//            ?.enqueue(object : Callback<FoodItems?> {
//                override fun onResponse(
//                    call: Call<FoodItems?>?,
//                    response: Response<FoodItems?>?
//                ) {
//                    bookingDetailsLiveData?.value = response?.body()
//                }
//
//                override fun onFailure(call: Call<FoodItems?>?, t: Throwable?) {
//                    bookingDetailsLiveData?.value = null
//                }
//            })
        return bookingDetailsLiveData
    }
}