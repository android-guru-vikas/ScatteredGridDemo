package com.vikas.scatteredgriddemo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
        var id: Int?,
        var category: List<String?>? = null,
        var category_name: List<String?>? = emptyList(),
        var category_color: String? = "#dedede",
        var description: String? = "",
        var poll_taken: String? = "",
        var cover_image: String? = "",
        var user_choice_id: Int?,
        var poll_media: List<PollMedia?>? = null,
        var poll_vote_percentage: List<PollPercentageItem?>? = null,
        var download_url: String? = null,
        var action_counts: ActionCounts? = null,
        var is_like: Boolean? = false,
        var is_subscribe: Boolean? = false,
        var item_type: Int?,
        var language: String? = "",
        var post_type: String? = "",
        var published_at: String? = "",
        var publisher: Publisher? = null,
        var score: Double?,
        var section_id: Int?,
        var fragment: String? = "",
        var thumbnail: String? = "",
        var title: String? = "",
        var duration: Int?,
        var name: String? = "",
        var uid: String? = "",
        var banner_image: String? = "",
        var vertical_poster: String? = "",
        var horizontal_poster: String? = "",
        var video: Video? = null,
        var horizontal_items: List<Item?>? = null,
        val state: String? = "",
        val filePath: String? = "",
        var upload_percentage: Int? = -1,
        val expert: Expert?,
        val product_url: String? = "",
        val action_url: String? = "",
        var is_playing: Boolean? = false
) : Parcelable

@Parcelize
data class PollMedia(
        val id: Int?,
        val image_file: String?,
        val overlay_text: String?
) : Parcelable

@Parcelize
data class PollPercentageItem(
        val count: Int?,
        val id: Int?,
        val percentage: Int?
) : Parcelable

@Parcelize
data class ActionCounts(
        val click: Int? = 1,
        var like: Int?,
        val share: Int?,
        val web_click: Int?
) : Parcelable

@Parcelize
data class Publisher(
        val name: String?,
        val thumbnail: String?,
        val uid: String?,
        var is_subscribe: Boolean? = false,
        var horizontal_items: List<Item?>? = null
) : Parcelable

@Parcelize
data class Video(
        val name: String? = "",
        val transcoding_id: String?,
        val url: String? = "",
        val duration: Int?,
        var is_live: Boolean? = false,
        var shoot_location: String?,
        val media_details: List<MediaDetails?>?,
        val aspect_ratio: String? = "",
        val publisher_name: String?,
        val publisher_thumbnail: String?,
        val language: String?,
        val post_id: String?,
        val thumbnail: String?
) : Parcelable

@Parcelize
data class MediaDetails(
        val id: Int?,
        val name: String? = "",
        val address: String? = "",
        val email: String? = "",
        val phone: String? = "",
        val location_link: String? = ""
) : Parcelable