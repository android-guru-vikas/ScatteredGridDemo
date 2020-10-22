package com.vikas.scatteredgriddemo.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vikas.scatteredgriddemo.R
import com.vikas.scatteredgriddemo.databinding.LayoutUserHomeMoreTabsBinding
import com.vikas.scatteredgriddemo.model.HomeTabsModel

class UserHomeMoreTabsAdapter(
    private val context: Context,
    private val models: List<HomeTabsModel>?
) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: LayoutUserHomeMoreTabsBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.layout_user_home_more_tabs,
            parent,
            false
        )
        return HomeTabViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as HomeTabViewHolder
        viewHolder.bindData(position)
    }

    override fun getItemCount(): Int {
        return models?.size!!
    }

    private inner class HomeTabViewHolder(var binding: LayoutUserHomeMoreTabsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            val model = models?.get(position)
            if (model != null) {
                binding.tvTitle.setText(model.title)
                binding.tvBody.setText(model.body)
                binding.ivIcon.setImageDrawable(model.thumbnail)
                val drawable = binding.parentView.background as GradientDrawable
                when (position) {
                    0 -> drawable.setColor(Color.parseColor("#e87589"))
                    1 -> drawable.setColor(Color.parseColor("#79b7de"))
                    2 -> drawable.setColor(Color.parseColor("#6387c1"))
                }
            }
        }

    }

}