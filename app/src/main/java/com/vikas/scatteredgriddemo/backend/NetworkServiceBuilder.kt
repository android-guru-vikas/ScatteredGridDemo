package com.vikas.scatteredgriddemo.backend

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vikas.scatteredgriddemo.BuildConfig
import com.vikas.scatteredgriddemo.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object NetworkServiceBuilder {
    private val okHttpClient: OkHttpClient? = OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())
            .addInterceptor(Interceptor { chain: Interceptor.Chain? ->
                val original = chain?.request()
                val request = getRequestBuilder(original)
//                        ?.header("Authorization", "Token " + AccountHelper.getToken())
                        ?.build()
                chain?.proceed(request)
            })
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            ?.build()
    private val okHttpClientForLogin: OkHttpClient? = OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())
            .addInterceptor(Interceptor { chain: Interceptor.Chain? ->
                val original = chain?.request()
                val request = getRequestBuilder(original)
                        ?.build()
                chain?.proceed(request)
            })
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    private val okHttpClientForRating: OkHttpClient? = OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())
            .addInterceptor(Interceptor { chain: Interceptor.Chain? ->
                val original = chain?.request()
                val request = getRequestBuilder(original)
                        ?.header("Authorization", "Token 5b7d9230e92e376d81ed8c29419eeefb10683c5f")
                        ?.build()
                chain?.proceed(request)
            })
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            ?.build()

    @JvmStatic
    fun buildMain(): NetworkService? {
        var client: OkHttpClient? = null
//        client = if (!AppUtils.isUserLoggedIn()) {
//            getInterceptorForLogin()
//        } else {
            Timber.d("Inside buildMain")
            getInterceptor()
//        }
        val gson: Gson = GsonBuilder().create()
        assert(client != null)
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.API_MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        return retrofit.create(NetworkService::class.java)
    }

    fun buildMainForLogin(): NetworkService? {
        val gson: Gson = GsonBuilder().create()
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.API_MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getInterceptorForLogin())
                .build()
        return retrofit.create(NetworkService::class.java)
    }

//    fun buildMainForRating(): NetworkService? {
//        val gson: Gson = GsonBuilder().create()
//        val retrofit: Retrofit = Retrofit.Builder()
//                .baseUrl(Constants.API_RATING_PLATFORM)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(getInterceptorForRating())
//                .build()
//        return retrofit.create(NetworkService::class.java)
//    }

    private fun getInterceptor(): OkHttpClient? {
        Timber.d("Inside buildMain getInterceptor : " + okHttpClient?.authenticator())
        return okHttpClient
    }

    private fun getInterceptorForLogin(): OkHttpClient? {
        return okHttpClientForLogin
    }

    private fun getInterceptorForRating(): OkHttpClient? {
        return okHttpClientForRating
    }

    private fun getRequestBuilder(request: Request?): Request.Builder? {
        return request?.newBuilder()
    }

    private fun getLoggingInterceptor(): HttpLoggingInterceptor? {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
    }
}