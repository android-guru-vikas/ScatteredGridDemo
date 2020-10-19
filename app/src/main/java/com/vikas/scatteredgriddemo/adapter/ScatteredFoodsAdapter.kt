package com.vikas.scatteredgriddemo.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vikas.scatteredgriddemo.LiciousDemoApplication
import com.vikas.scatteredgriddemo.LiciousDemoApplication.Companion.getInstance
import com.vikas.scatteredgriddemo.R
import com.vikas.scatteredgriddemo.activity.BaseActivity
import com.vikas.scatteredgriddemo.databinding.LayoutItemFoodHorizontalBinding
import com.vikas.scatteredgriddemo.glide.GlideAppController
import com.vikas.scatteredgriddemo.model.Product
import com.vikas.scatteredgriddemo.utils.AppUtils
import timber.log.Timber

class ScatteredFoodsAdapter(context: BaseActivity<*>?, resultList: MutableList<Product?>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private var itemArrayList: MutableList<Product?>? = resultList
    private val baseActivity: BaseActivity<*>? = context
    private val inflater: LayoutInflater =
        context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        val binding: LayoutItemFoodHorizontalBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.layout_item_food_horizontal,
            viewGroup,
            false
        )
        return HorizontalFeedsViewHolder(baseActivity, binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val result = itemArrayList?.get(i)
        if (result != null) {
            val posterViewHolder = viewHolder as HorizontalFeedsViewHolder
            posterViewHolder.bindData(result)
        }
    }

    override fun getItemCount(): Int {
        return itemArrayList?.size!!
    }

    inner class HorizontalFeedsViewHolder internal constructor(
        private val activity: BaseActivity<*>?,
        private val binding: LayoutItemFoodHorizontalBinding?
    ) : RecyclerView.ViewHolder(binding?.root!!) {
        fun bindData(item: Product?) = try {
            if (item != null) {
                val vto = binding?.thumbnailImg?.viewTreeObserver
                vto?.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        binding?.thumbnailImg?.viewTreeObserver?.removeOnPreDrawListener(this)
                        val thumbnailUrl =
                            item?.product_merchantdising?.pr_image + "?width=" + binding?.thumbnailImg?.measuredWidth + "&aspect_ratio=9:16"
                        Timber.d("Inside ThumbnailUrl : $thumbnailUrl")
                        GlideAppController.setProductImage(
                            binding?.thumbnailImg,
                            activity,
                            thumbnailUrl
                        )
                        return true
                    }
                })

                binding?.titleTv?.text = item?.product_master?.pr_name
                binding?.weightTv?.text = "Net wt. " + item?.product_master?.net
                binding?.priceTv?.text =
                    "₹ " + AppUtils.roundToTwoDigit((item.product_pricing?.unit_gram?.let {
                        item.product_pricing?.price_gram?.times(it)
                    }).toString()?.toDouble())

                binding?.totalPriceTv?.text =
                    "₹ " + item.product_pricing?.base_price?.toDouble()?.let {
                        AppUtils.roundToTwoDigit(
                            it
                        )
                    }
                binding?.totalPriceTv?.paintFlags =
                    binding?.totalPriceTv?.paintFlags?.or(Paint.STRIKE_THRU_TEXT_FLAG)!!

                var currentItemCount = 0
                binding?.removeItemImgView.setOnClickListener(View.OnClickListener {
                    //Remove item
                    if (currentItemCount == 1) {
                        currentItemCount = currentItemCount?.minus(1)
                        binding?.itemCountTv.text = (currentItemCount).toString()
                        binding?.updateCardLayout.visibility = View.GONE
                        binding?.addToCartBtn.visibility = View.VISIBLE
                    } else {
                        //Show msg
                        currentItemCount = currentItemCount?.minus(1)
                        binding?.itemCountTv.text = (currentItemCount).toString()
                    }

                })

                binding?.addItemImgView.setOnClickListener(View.OnClickListener {
                    //Add item
                    if (currentItemCount < item?.product_inventory?.stock_units!!) {
                        currentItemCount = currentItemCount?.plus(1)
                        binding?.itemCountTv.text = (currentItemCount).toString()
                    } else {
                        Toast.makeText(baseActivity, "Can not add more items", Toast.LENGTH_LONG).show()
                    }

                })

                binding?.addToCartBtn.setOnClickListener(View.OnClickListener {
                    //Add item
                    currentItemCount = currentItemCount?.plus(1)
                    binding?.itemCountTv.text = (currentItemCount).toString()
                    binding?.updateCardLayout.visibility = View.VISIBLE
                    binding?.addToCartBtn.visibility = View.GONE
                })
            } else
                Toast.makeText(baseActivity, "Something went wrong", Toast.LENGTH_LONG)
                    .show()
        } catch (e: Exception) {
            Timber.d("Inside exception : " + e.message)
        }
    }
}