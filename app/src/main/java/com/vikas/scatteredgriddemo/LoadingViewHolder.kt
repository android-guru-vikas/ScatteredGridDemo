package com.vikas.scatteredgriddemo

import androidx.recyclerview.widget.RecyclerView
import com.vikas.scatteredgriddemo.databinding.ItemProgressbarBinding
import timber.log.Timber

class LoadingViewHolder constructor(var binding: ItemProgressbarBinding?) : RecyclerView.ViewHolder(binding?.root!!) {
    fun bindData() {
        Timber.d("Inside progress")
    }
}