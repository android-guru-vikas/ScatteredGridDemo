package com.vikas.scatteredgriddemo.model

import android.graphics.drawable.Drawable

data class HomeFeedModel(
        val section_id: Int?,
        val items: List<Item>?,
        val item_count: Int?
)

data class HomeItemTitle(
        val title: String?,
        val tag: String?,
        val isShowAll: Boolean?
)

data class EmptyHintService(
        val hintHeader: String?,
        val hintFooter: String?,
        val buttonText: String?
)

data class EmptyHintYourContent(
        val hintHeader: String?
)

data class BrowseAll(
        val title: String?,
        val tag: String?
)

data class HomeTabsModel(
        val title: String?,
        val body: String?,
        val thumbnail: Drawable?
)

data class ExpertClientsModel(
        val section_id: Int?,
        val items: List<Expert?>?,
        val item_count: Int?
)