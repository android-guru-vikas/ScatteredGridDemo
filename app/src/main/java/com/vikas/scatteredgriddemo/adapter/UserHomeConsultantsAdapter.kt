package com.vikas.scatteredgriddemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vikas.scatteredgriddemo.R
import com.vikas.scatteredgriddemo.databinding.LayoutItemUserConsultantsBinding
import com.vikas.scatteredgriddemo.glide.GlideAppController
import com.vikas.scatteredgriddemo.model.Expert

class UserHomeConsultantsAdapter(
    private val context: Context,
    private val clientList: List<Expert?>?
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: LayoutItemUserConsultantsBinding =
            DataBindingUtil.inflate(inflater, R.layout.layout_item_user_consultants, parent, false)
        return ConsultantsViewHolder(context, binding)
    }

    override fun getItemCount(): Int {
        return clientList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val consultantsViewHolder = holder as ConsultantsViewHolder
        consultantsViewHolder.bindData(position)
    }

    inner class ConsultantsViewHolder internal constructor(
        private val context: Context,
        private val binding: LayoutItemUserConsultantsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            val expert = clientList?.get(position)
            GlideAppController.setUserImage(binding.expertImgView, context, expert?.profile_pic)
            binding.expertNameTv.text = expert?.name
            binding.tvBookingStats.text =
                expert?.booking_stats?.PE.toString() + " Pending Bookings, " + expert?.booking_stats?.SD.toString() + " Completed"
        }
    }
}
