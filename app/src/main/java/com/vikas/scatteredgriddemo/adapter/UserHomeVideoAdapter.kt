package com.vikas.scatteredgriddemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vikas.scatteredgriddemo.LoadingViewHolder
import com.vikas.scatteredgriddemo.R
import com.vikas.scatteredgriddemo.databinding.ItemProgressbarBinding
import com.vikas.scatteredgriddemo.databinding.LayoutItemVideoHorizontalBinding
import com.vikas.scatteredgriddemo.databinding.LayoutItemVideoSquareBinding
import com.vikas.scatteredgriddemo.databinding.LayoutItemVideoVerticalBinding
import com.vikas.scatteredgriddemo.glide.GlideAppController
import com.vikas.scatteredgriddemo.model.Item
import com.vikas.scatteredgriddemo.utils.AppUtils
import com.vikas.scatteredgriddemo.utils.Constants
import timber.log.Timber

class UserHomeVideoAdapter(private val context: Context, private var itemArrayList: List<Item?>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return when (i) {
            Constants.POSTER_TYPE -> {
                val binding: LayoutItemVideoVerticalBinding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.layout_item_video_vertical,
                    viewGroup,
                    false
                )
                VerticalFeedsViewHolder(context, binding)
            }
            Constants.VIEW_TYPE_HORIZONTAL -> {
                val binding: LayoutItemVideoHorizontalBinding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.layout_item_video_horizontal,
                    viewGroup,
                    false
                )
                HorizontalFeedsViewHolder(context, binding)
            }
            Constants.VIEW_TYPE_SQUARE -> {
                val binding: LayoutItemVideoSquareBinding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.layout_item_video_square,
                    viewGroup,
                    false
                )
                return SquareFeedsViewHolder(context, binding)
            }
            else -> {
                val binding: ItemProgressbarBinding =
                    DataBindingUtil.inflate(inflater, R.layout.item_progressbar, viewGroup, false)
                LoadingViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val result = itemArrayList?.get(i)
        val itemType = viewHolder.itemViewType
        if (result != null) {
            when (itemType) {
                Constants.POSTER_TYPE -> {
                    val posterViewHolder = viewHolder as VerticalFeedsViewHolder
                    posterViewHolder.bindData(result)
                }
                Constants.VIEW_TYPE_HORIZONTAL -> {
                    val posterViewHolder = viewHolder as HorizontalFeedsViewHolder
                    posterViewHolder.bindData(result)
                }
                Constants.VIEW_TYPE_SQUARE -> {
                    val posterViewHolder = viewHolder as SquareFeedsViewHolder
                    posterViewHolder.bindData(result)
                }
                else -> {
                    val loadingViewHolder = viewHolder as LoadingViewHolder
                    loadingViewHolder.bindData()
                }
            }
        } else {
            val loadingViewHolder = viewHolder as LoadingViewHolder
            loadingViewHolder.bindData()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemArrayList?.get(position) != null) {
            val video = itemArrayList?.get(position)?.video
            if (video != null) {
                if (AppUtils.getValueFromData(video.aspect_ratio).equals("V", ignoreCase = true)) {
                    Constants.POSTER_TYPE
                } else if (AppUtils.getValueFromData(video.aspect_ratio)
                        .equals("H", ignoreCase = true)
                ) {
                    Constants.VIEW_TYPE_HORIZONTAL
                } else if (!AppUtils.isNotEmptyNotNull(video.aspect_ratio)) {
                    Constants.POSTER_TYPE
                } else if (AppUtils.getValueFromData(video.aspect_ratio)
                        .equals("S", ignoreCase = true)
                ) {
                    return Constants.VIEW_TYPE_SQUARE
                } else {
                    Constants.VIEW_TYPE_LOADING
                }
            } else {
                Constants.VIEW_TYPE_LOADING
            }
        } else {
            Constants.VIEW_TYPE_LOADING
        }
    }

    override fun getItemCount(): Int {
        if (itemArrayList != null){
           return itemArrayList?.size!!
        }else{
            return 0
        }
    }

    inner class VerticalFeedsViewHolder internal constructor(
        private val context: Context,
        private val binding: LayoutItemVideoVerticalBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Item?) {
            try {
                if (item != null) {
                    val vto = binding.videoThumbnailImg.viewTreeObserver
                    vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                        override fun onPreDraw(): Boolean {
                            binding.videoThumbnailImg.viewTreeObserver.removeOnPreDrawListener(this)
                            val thumbnailUrl =
                                item.thumbnail + "?height=" + binding.videoThumbnailImg.measuredHeight + "&width=" + binding.videoThumbnailImg.measuredWidth
                            Timber.d("Inside ThumbnailUrl : $thumbnailUrl")
                            GlideAppController.setVerticalBanner(
                                binding.videoThumbnailImg,
                                context,
                                thumbnailUrl
                            )
                            return true
                        }
                    })
                }
            } catch (e: Exception) {
                Timber.d("Inside exception : " + e.message)
            }
        }

    }

    inner class HorizontalFeedsViewHolder internal constructor(
        private val context: Context,
        private val binding: LayoutItemVideoHorizontalBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Item?) {
            try {
                if (item != null) {
                    val vto = binding.videoThumbnailImg.viewTreeObserver
                    vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                        override fun onPreDraw(): Boolean {
                            binding.videoThumbnailImg.viewTreeObserver.removeOnPreDrawListener(this)
                            val thumbnailUrl =
                                item.thumbnail + "?height=" + binding.videoThumbnailImg.measuredHeight + "&width=" + binding.videoThumbnailImg.measuredWidth
                            Timber.d("ThumbnailUrl :HorizontalFeedsViewHolder%s", thumbnailUrl)
                            GlideAppController.setHomeBanner(
                                binding.videoThumbnailImg,
                                context,
                                thumbnailUrl,
                                binding.videoThumbnailImg.measuredWidth,
                                binding.videoThumbnailImg.measuredHeight
                            )
                            return true
                        }
                    })
                    var name: String? = ""
                    var profile: String? = ""
                    var bio: String? = ""
                    binding.expertNameTv.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        null,
                        null
                    )
                    if (item.expert != null) {
                        binding.tvBio.visibility = View.VISIBLE
                        binding.tvMessage.visibility = View.VISIBLE
                        name = AppUtils.getValueFromData(item.expert.name)
                        profile = AppUtils.getValueFromData(item.expert.profile_pic)
                    } else if (item.publisher != null) {
                        binding.tvBio.visibility = View.GONE
                        binding.tvMessage.visibility = View.GONE
                        name = AppUtils.getValueFromData(item.publisher?.name)
                        profile = AppUtils.getValueFromData(item.publisher?.thumbnail)
                    }
                    GlideAppController.setUserImage(binding.expertImgView, context, profile)
                    binding.expertNameTv.setText(name)
                    binding.tvBio.text = bio
                    binding.tvItemTitle.setText("" + AppUtils.getValueFromData(item.title))
                    binding.tvViewCount.setText(item.action_counts?.web_click?.toString())
                }
            } catch (e: Exception) {
                Timber.d("Inside exception : " + e.message)
            }
        }

    }

    inner class SquareFeedsViewHolder internal constructor(
        private val context: Context,
        private val binding: LayoutItemVideoSquareBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Item?) {
            try {
                if (item != null) {
                    val vto: ViewTreeObserver = binding.videoThumbnailImg.viewTreeObserver
                    vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                        override fun onPreDraw(): Boolean {
                            binding.videoThumbnailImg.viewTreeObserver.removeOnPreDrawListener(this)
                            val thumbnailUrl =
                                (item.thumbnail + "?width=" + binding.videoThumbnailImg.measuredWidth
                                        + "&aspect_ratio=1:1")
                            Timber.d("ThumbnailUrl :SquareFeedsViewHolder%s", thumbnailUrl)
                            GlideAppController.setHomeBanner(
                                binding.videoThumbnailImg,
                                context,
                                thumbnailUrl,
                                binding.videoThumbnailImg.measuredWidth,
                                binding.videoThumbnailImg.measuredHeight
                            )
                            return true
                        }
                    })
                }
            } catch (e: java.lang.Exception) {
                Timber.d("Inside exception : " + e.message)
            }
        }
    }


}