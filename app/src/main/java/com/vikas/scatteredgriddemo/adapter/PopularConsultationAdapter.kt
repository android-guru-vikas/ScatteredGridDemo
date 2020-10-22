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
import com.vikas.scatteredgriddemo.databinding.PopularConsultationsLayoutBinding
import com.vikas.scatteredgriddemo.model.CategoryModelServices
import timber.log.Timber

class PopularConsultationAdapter(
    private val context: Context,
    private val consultationsList: List<CategoryModelServices?>?
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: PopularConsultationsLayoutBinding =
            DataBindingUtil.inflate(inflater, R.layout.popular_consultations_layout, parent, false)
        return PopularConstViewHolder(context, binding)
    }

    override fun getItemCount(): Int {
        return consultationsList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val headerViewHolder = holder as PopularConstViewHolder
        headerViewHolder.bindData(position)
    }

    inner class PopularConstViewHolder internal constructor(
        private val context: Context,
        private val binding: PopularConsultationsLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            val popularConstModel: CategoryModelServices? = consultationsList?.get(position)
            val drawable = binding.parent.background as GradientDrawable
            drawable.setColor(Color.WHITE)
            binding.tvTitle.text = popularConstModel?.name


            val vto: ViewTreeObserver = binding.ivIcon.viewTreeObserver
            vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    binding.ivIcon.viewTreeObserver.removeOnPreDrawListener(this)
                    val width = binding.ivIcon.measuredWidth
                    val height = binding.ivIcon.measuredHeight
                    val thumbnailUrl: String =
                        popularConstModel?.thumbnail + "?height=" + height + "&width=" + width
                    Timber.d("Inside ThumbnailUrl : $thumbnailUrl")

                    Glide.with(context)
                        .asBitmap()
                        .load(thumbnailUrl)
                        .centerCrop()
                        .transition(GenericTransitionOptions.with(R.anim.bt_glide_anim))
                        .thumbnail(0.4f)
                        .into(object : BitmapImageViewTarget(binding.ivIcon) {
                            override fun onLoadFailed(errorDrawable: Drawable?) {
                                super.onLoadFailed(errorDrawable)
                            }

                            override fun onResourceReady(
                                resource: Bitmap,
                                transition: Transition<in Bitmap>?
                            ) {
                                binding.ivIcon.setImageBitmap(resource)
                            }
                        })
                    return true
                }
            })
        }
    }
}