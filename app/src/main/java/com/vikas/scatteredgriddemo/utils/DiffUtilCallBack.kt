package com.vikas.scatteredgriddemo.utils

import androidx.recyclerview.widget.DiffUtil
import com.vikas.scatteredgriddemo.model.Item

class DiffUtilCallBack : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem.section_id == newItem.section_id

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem
}