package com.vikas.scatteredgriddemo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExpertServicesResponse(
        val id: Int?,
        val expert_user: Expert?,
        var user: Expert? = null,
        var order_id: String?,
        var payment_status: String?,
        var booking_status: String?,
        var short_url: String?,
        var agora_token: String?,
        var agora_channel_id: String?,
        var amount_user_paid: Float?,
        var coupon_code: String?,
        var created_at: String?,
        var updated_at: String?,
        val service: ExpertServicesResponse? = null,
        var total_sessions: Int?,
        var original_expert_price: Float?,
        var expert_price: Float?,
        var upmark_percentage: Float?,
        var upmarked_price: Float?,
        var general_discount: Float?,
        var ihfee_percentage: Float?,
        var discounted_price: Float?,
        var discounted_ihfee_price: Float?,
        var expert_coupon_percentage: Float?,
        var discounted_expert_coupon_price: Float?,
        var discounted_expert_coupon_price_ihfee: Float?,
        var expert_lead_reward_percentage: Float?,
        var hsn_lead_commision_percentage: Float?,
        var expert_lead_payout: Float?,
        var hsn_lead_payout_init: Float?,
        var earn_expert_lead: Float?,
        var hsn_lead_payout: Float?,
        val title: String?,
        val description: String?,
        val short_description: String?,
        val language: String?,
        val thumbnail: String?,
        val banner: String?,
        val slug_title: String?,
        val slug: String?,
        val availability: String?,
        val fee_structure: String?,
        val is_live: Boolean?,
        val publishing_state: Int?,
        val payment_url: String?,
        val service_type: String?,
        val timing: String?,
        val meeting_link: String?,
        var service_categories: List<CategoryModelServices?>?
) : Parcelable

@Parcelize
data class OngoingBookingModel(
        val expert: Expert?,
        val token: String?,
        val channel: String?,
        val isVideoCall: Boolean?
) : Parcelable

@Parcelize
data class BookingExistsModel(
        val booking_exists: Boolean?,
        val booking_details: ExpertServicesResponse?
) : Parcelable

data class CategoryModels(
        val count: Int?,
        val next: String?,
        val previous: String?,
        val results: List<CategoryModelServices?>?
)

@Parcelize
data class CategoryModelServices(
        var id: Int?,
        var name: String?,
        var slug: String?,
        var slug_title: String?,
        var description: String?,
        var language: String?,
        var thumbnail: String?,
        var is_live: Boolean?,
        var ordering: Int?,
        var category_type: String?,
        var created_at: String?,
        var updated_at: String?,
        var isSelected: Boolean? = false
) : Parcelable