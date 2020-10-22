package com.vikas.scatteredgriddemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.vikas.scatteredgriddemo.R
import com.vikas.scatteredgriddemo.databinding.PosterItemLayoutBinding
import com.vikas.scatteredgriddemo.model.Banner
import timber.log.Timber

class UserBannerAdapter(private val context: Context?, private val posterList: MutableList<Banner?>?) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private val inflater: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        val binding: PosterItemLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.poster_item_layout, viewGroup, false)
        return PosterListViewHolder(context, binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val poster = posterList?.get(i)
        val posterListViewHolder = viewHolder as PosterListViewHolder
        posterListViewHolder.bindData(poster)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return posterList?.size!!
    }

    private inner class PosterListViewHolder internal constructor(var context: Context?, var binding: PosterItemLayoutBinding?) : RecyclerView.ViewHolder(binding?.root!!) {
        fun bindData(poster: Banner?) {
            val vto = binding?.posterImage?.viewTreeObserver
            vto?.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    binding?.posterImage?.viewTreeObserver?.removeOnPreDrawListener(this)
                    val thumbnailUrl = poster?.image + "?height=" + binding?.posterImage?.measuredHeight + "&width=" + binding?.posterImage?.measuredWidth
                    Timber.d("Inside ThumbnailUrl : $thumbnailUrl")
                    context?.let {
                        binding?.posterImage?.let { it1 ->
                            Glide.with(it)
                                    .load(thumbnailUrl)
                                    .apply(RequestOptions().transform(FitCenter(), RoundedCorners((context?.resources?.getDimension(R.dimen._4sdp))?.toInt()!!)))
                                    .placeholder(R.drawable.toolbar_gredient_new)
                                    .error(R.drawable.toolbar_gredient_new)
                                    .into(it1)
                        }
                    }
                    return true
                }
            })
        }

    }

    init {
    }
}