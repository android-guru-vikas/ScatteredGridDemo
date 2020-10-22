package com.vikas.scatteredgriddemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vikas.scatteredgriddemo.R
import com.vikas.scatteredgriddemo.activity.MainActivity
import com.vikas.scatteredgriddemo.databinding.MainAdapterBinding
import com.vikas.scatteredgriddemo.model.Item
import com.vikas.scatteredgriddemo.utils.DiffUtilCallBack

class MainAdapter(context: MainActivity) :
    PagingDataAdapter<Item, MainAdapter.MyViewHolder>(DiffUtilCallBack()) {

    private val context: MainActivity? = context

    // OVERRIDE ---
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: MainAdapterBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.main_adapter, parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, context) }
    }

    override fun getItemViewType(position: Int): Int = position

    class MyViewHolder(private val binding: MainAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photoList: Item, context: Context?) {
//            if (context != null) {
//                GlideApp.with(context)
//                    .asBitmap()
//                    .thumbnail(0.4f)
//                    .centerInside()
//                    .load(photoList.items + "?height=576&width=1018")
//                    .skipMemoryCache(false)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .placeholder(R.drawable.toolbar_gredient_new)
//                    .into(binding.imageView)
//            }
//            binding.dataManager = photoList
        }
    }
}