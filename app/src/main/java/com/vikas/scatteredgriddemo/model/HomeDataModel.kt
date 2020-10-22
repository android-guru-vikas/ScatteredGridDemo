package com.vikas.scatteredgriddemo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Expert(
        val bio: String? = "",
        val email: String?,
        val id: Int?,
        val slug: String?,
        val banner: String?,
        val short_bio: String?,
        val is_expert: Boolean?,
        val is_profile_pic_set: Boolean?,
        val is_tick_verified: Boolean? = false,
        val language: String?,
        val expert_verification_state: Int?,
        val name: String?,
        val profile_pic: String?,
        var user_id: String? = "",
        val order: Int?,
        val gender: String?,
        val location: String?,
        val expert_categories: List<ExpertCategories?>?,
        val intro_video_url: String?,
        val payment_url: String?,
        var is_read: Boolean? = true,
        val country_code: String? = "+91",
        val phone: String?,
        val booking_stats: BookingStats?,
        val last_booking_date: String?
) : Parcelable


@Parcelize
data class ExpertCategories(
        val id: String?,
        val name: String?,
        val description: String?,
        val image_url: String?,
        val language: String?
) : Parcelable

@Parcelize
data class Banner(
        val name: String?,
        val description: String?,
        val language: String?,
        val action_url: String?,
        val image: String?,
        val is_live: Boolean?
) : Parcelable

@Parcelize
data class BookingStats(
        val PE: Int?,
        val SD: Int?
):Parcelable