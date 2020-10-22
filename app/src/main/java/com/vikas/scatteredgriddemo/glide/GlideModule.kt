package com.vikas.scatteredgriddemo.glide

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.load.engine.executor.GlideExecutor
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import okhttp3.OkHttpClient
import timber.log.Timber
import java.io.InputStream
import java.util.concurrent.TimeUnit

/**
 * Created by Vikas on 1/13/18.
 */
@GlideModule
class GlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder?.setLogLevel(Log.DEBUG)
        val memoryCacheSizeBytes = 1024 * 1024 * 500 // 500mb
        builder?.setMemoryCache(LruResourceCache(memoryCacheSizeBytes.toLong()))
        builder?.setDiskCache(InternalCacheDiskCacheFactory(context, memoryCacheSizeBytes.toLong()))
        builder?.setDefaultRequestOptions(requestOptions())
        val myUncaughtThrowableStrategy = UncaughtThrowableStrategy { t: Throwable? -> Timber.d("Inside apply options : " + t?.message) }
        builder?.setDiskCacheExecutor(GlideExecutor.newDiskCacheExecutor(myUncaughtThrowableStrategy))
        builder?.setResizeExecutor(GlideExecutor.newSourceExecutor(myUncaughtThrowableStrategy))
        val calculator = MemorySizeCalculator.Builder(context)
                .setBitmapPoolScreens(3f)
                .build()
        builder?.setBitmapPool(LruBitmapPool(calculator.bitmapPoolSize.toLong()))
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val client = OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
        val factory = OkHttpUrlLoader.Factory(client)
        glide.registry.replace(GlideUrl::class.java, InputStream::class.java, factory)
    }

    companion object {
        fun requestOptions(): RequestOptions? {
            return RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .format(DecodeFormat.PREFER_RGB_565)
                    .centerCrop()
                    .skipMemoryCache(true)
        }
    }
}