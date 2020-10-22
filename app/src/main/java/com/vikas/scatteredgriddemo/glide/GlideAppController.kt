package com.vikas.scatteredgriddemo.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.vikas.scatteredgriddemo.R
import timber.log.Timber

object GlideAppController {

    fun setHomeBanner(imageView: ImageView?, context: Context?, url: String?, width: Int, height: Int) {
        if (context != null) {
            try {
                GlideApp.with(context)
                    .asBitmap()
                    .load(url)
                    .centerInside()
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.toolbar_gredient_new)
                    .override(width, height)
                    .into(object : BitmapImageViewTarget(imageView) {
                        override fun onResourceReady(bitmap: Bitmap, anim: Transition<in Bitmap?>?) {
                            super.onResourceReady(bitmap, anim)
                            imageView?.setImageBitmap(bitmap)
                        }

                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            super.onLoadFailed(errorDrawable)
                            Timber.d("Inside setHomeBanner ex")
                            imageView?.setImageDrawable(errorDrawable)
                        }
                    })
            } catch (e: Exception) {
                Timber.d("Inside setImageOnView ex : " + e.message)
            }
        }
    }

    fun setUserImage(imageView: ImageView?, context: Context?, url: String?) {
        if (context != null) {
            try {
                GlideApp.with(context)
                    .asBitmap()
                    .load(url)
                    .override(100,100)
                    .placeholder(R.drawable.toolbar_gredient_new)
                    .error(R.drawable.toolbar_gredient_new)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .thumbnail(0.4f)
                    .into(object : BitmapImageViewTarget(imageView) {
                        override fun onResourceReady(bitmap: Bitmap, anim: Transition<in Bitmap?>?) {
                            super.onResourceReady(bitmap, anim)
                            imageView?.setImageBitmap(bitmap)
                        }

                    })
            } catch (e: Exception) {
                Timber.d("Inside setImageOnView ex : " + e.message)
            }
        }
    }

    fun setVerticalBanner(imageView: ImageView?, context: Context?, url: String?) {
        if (context != null) {
            try {
                GlideApp.with(context)
                    .asBitmap()
                    .load(url)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.toolbar_gredient_new)
                    .into(object : BitmapImageViewTarget(imageView) {
                        override fun onResourceReady(bitmap: Bitmap, anim: Transition<in Bitmap?>?) {
                            super.onResourceReady(bitmap, anim)
                            imageView?.setImageBitmap(bitmap)
                        }

                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            super.onLoadFailed(errorDrawable)
                            Timber.d("Inside setHomeBanner ex")
                            imageView?.setImageDrawable(errorDrawable)
                        }
                    })
            } catch (e: Exception) {
                Timber.d("Inside setImageOnView ex : " + e.message)
            }
        }
    }
}