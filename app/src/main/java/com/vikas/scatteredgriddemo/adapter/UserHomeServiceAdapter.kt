package com.vikas.scatteredgriddemo.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.vikas.scatteredgriddemo.R
import com.vikas.scatteredgriddemo.databinding.ServiceItemHomeLayoutBinding
import com.vikas.scatteredgriddemo.model.ExpertServicesResponse
import timber.log.Timber

class UserHomeServiceAdapter(
    private val context: Context,
    private val headerList: List<ExpertServicesResponse?>?
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ServiceItemHomeLayoutBinding =
            DataBindingUtil.inflate(inflater, R.layout.service_item_home_layout, parent, false)
        return HomeServiceViewHolder(context, binding)
    }

    override fun getItemCount(): Int {
        return headerList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val headerViewHolder = holder as HomeServiceViewHolder
        headerViewHolder.bindData(position)
    }

    inner class HomeServiceViewHolder internal constructor(
        private val context: Context,
        private val binding: ServiceItemHomeLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            val serviceModel: ExpertServicesResponse? = headerList?.get(position)
            var title = ""
            var banner = ""
            if (serviceModel?.service != null) {
                title = serviceModel.service.title.toString()
                banner = serviceModel.service.banner.toString()
            } else {
                title = serviceModel?.title.toString()
                banner = serviceModel?.banner.toString()
                if (serviceModel?.publishing_state == 1) {
                    binding.bookBtn.setTextColor(Color.parseColor("#fc2852"))
                } else {
                    binding.bookBtn.setTextColor(Color.parseColor("#9E9E9E"))
                }
            }
            val drawable = binding.clParent.background as GradientDrawable
            drawable.setColor(Color.WHITE)
            binding.tvServiceTitle.text = title


            val vto: ViewTreeObserver = binding.ivThumbnail.viewTreeObserver
            vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    binding.ivThumbnail.viewTreeObserver.removeOnPreDrawListener(this)
                    val width = binding.ivThumbnail.measuredWidth
                    val height = binding.ivThumbnail.measuredHeight
                    val thumbnailUrl = "$banner?height=$height&width=$width"
                    Timber.d("Inside ThumbnailUrl : $thumbnailUrl")

                    Glide.with(context)
                        .asBitmap()
                        .load(thumbnailUrl)
                        .centerCrop()
                        .transition(GenericTransitionOptions.with(R.anim.bt_glide_anim))
                        .thumbnail(0.4f)
                        .into(object : BitmapImageViewTarget(binding.ivThumbnail) {
                            override fun onLoadFailed(errorDrawable: Drawable?) {
                                super.onLoadFailed(errorDrawable)
                            }

                            override fun onResourceReady(
                                resource: Bitmap,
                                transition: Transition<in Bitmap>?
                            ) {
                                binding.ivThumbnail.setImageBitmap(resource)
                            }
                        })
                    return true
                }
            })
        }
    }
}
