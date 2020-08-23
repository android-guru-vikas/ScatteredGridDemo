package com.vikas.scatteredgriddemo.activity

import android.os.Bundle
import android.text.TextUtils
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.vikas.scatteredgriddemo.*
import com.vikas.scatteredgriddemo.adapter.ScatteredFoodsAdapter
import com.vikas.scatteredgriddemo.model.FoodItems
import com.vikas.scatteredgriddemo.model.LiciousViewModel
import com.vikas.scatteredgriddemo.model.Product
import com.vikas.scatteredgriddemo.utils.AppUtils
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity<LiciousViewModel>() {

    private var mLayoutManager: StaggeredGridLayoutManager? = null
    private var foodsAdapter: ScatteredFoodsAdapter? = null

    override fun getViewModel(): Class<LiciousViewModel> {
        return LiciousViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        liciousViewModel = viewModel as LiciousViewModel

        val jsonString: String? = AppUtils.loadSettingsJsonFile(pContext)
        if (!TextUtils.isEmpty(jsonString)) {
            val gson = Gson()
            val foodItems = gson.fromJson<FoodItems>(jsonString, FoodItems::class.java)
            val filters = foodItems?.data?.filters
            if (!filters.isNullOrEmpty()) {
                allSlotsTv.text = filters[0]?.title
                todaySlotTv.text = filters[1]?.title
            }
            toolBarTv.text = foodItems?.data?.title
            itemHeaderTv.text = foodItems?.data?.info_message
            itemCountTv.text = "(" + foodItems?.data?.info_badge + ")"
            mLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            itemsRv?.layoutManager = mLayoutManager
            foodsAdapter = ScatteredFoodsAdapter(
                this,
                foodItems?.data?.products as MutableList<Product?>?
            )
            itemsRv?.adapter = foodsAdapter
        }
    }
}