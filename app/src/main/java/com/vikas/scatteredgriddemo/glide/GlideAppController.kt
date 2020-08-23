package com.vikas.scatteredgriddemo.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.vikas.scatteredgriddemo.R
import timber.log.Timber

object GlideAppController {

    fun setProductImage(imageView: ImageView?, context: Context?, url: String?) {
        if (context != null) {
            try {
                GlideApp.with(context)
                    .asBitmap()
                    .load(url)
                    .centerInside()
                    .placeholder(R.drawable.toolbar_gredient_new)
                    .transition(GenericTransitionOptions.with(R.anim.bt_glide_anim))
                    .thumbnail(0.5f)
                    .into(object : BitmapImageViewTarget(imageView) {
                        override fun onResourceReady(
                            bitmap: Bitmap,
                            anim: Transition<in Bitmap?>?
                        ) {
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