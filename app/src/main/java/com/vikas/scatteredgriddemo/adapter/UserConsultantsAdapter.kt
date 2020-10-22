package com.vikas.scatteredgriddemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vikas.scatteredgriddemo.LoadingViewHolder
import com.vikas.scatteredgriddemo.R
import com.vikas.scatteredgriddemo.databinding.ItemProgressbarBinding
import com.vikas.scatteredgriddemo.databinding.LayoutItemUserConsultantsBinding
import com.vikas.scatteredgriddemo.glide.GlideAppController
import com.vikas.scatteredgriddemo.model.Expert
import com.vikas.scatteredgriddemo.utils.Constants
import timber.log.Timber

class UserConsultantsAdapter(
    private val context: Context,
    private val clientList: List<Expert?>?,
    private val clickListener: UserConsultantsClickInterface
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == Constants.POSTER_TYPE) {
            val binding: LayoutItemUserConsultantsBinding =
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.layout_item_user_consultants,
                    parent,
                    false
                )
            ConsultantsViewHolder(context, binding)
        } else {
            val binding: ItemProgressbarBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_progressbar, parent, false)
            LoadingViewHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (clientList?.get(position) != null) {
            Timber.d("item_type  Constants.POSTER_TYPE")
            Constants.POSTER_TYPE
        } else {
            Timber.d("item_type  Constants.VIEW_TYPE_LOADING")
            Constants.VIEW_TYPE_LOADING
        }
    }

    override fun getItemCount(): Int {
        return clientList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val expert: Expert? = clientList?.get(position)
        val itemType = holder.itemViewType
        if (expert != null) {
            if (itemType == Constants.POSTER_TYPE) {
                val consultantsViewHolder = holder as ConsultantsViewHolder
                consultantsViewHolder.bindData(position)
            } else {
                val loadingViewHolder = holder as LoadingViewHolder
                loadingViewHolder.bindData()
            }
        } else {
            val loadingViewHolder = holder as LoadingViewHolder
            loadingViewHolder.bindData()
        }
    }

    inner class ConsultantsViewHolder internal constructor(
        private val context: Context,
        private val binding: LayoutItemUserConsultantsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            val expert = clientList?.get(position)
            GlideAppController.setUserImage(
                binding.expertImgView,
                context,
                expert?.profile_pic
            )
            binding.expertNameTv.text = expert?.name
            binding.tvBookingStats.text =
                expert?.booking_stats?.PE.toString() + " Pending Bookings, " + expert?.booking_stats?.SD.toString() + " Completed"
        }
    }

    interface UserConsultantsClickInterface {
        fun onConsultantItemClick(expert: Expert?, tag: String?)
    }
}
