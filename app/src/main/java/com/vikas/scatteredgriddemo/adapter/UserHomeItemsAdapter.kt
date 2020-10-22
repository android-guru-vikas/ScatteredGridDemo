package com.vikas.scatteredgriddemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vikas.scatteredgriddemo.LoadingViewHolder
import com.vikas.scatteredgriddemo.R
import com.vikas.scatteredgriddemo.databinding.*
import com.vikas.scatteredgriddemo.model.*
import com.vikas.scatteredgriddemo.utils.Constants
import com.vikas.scatteredgriddemo.utils.DiffUtilCallBack
import timber.log.Timber

class UserHomeItemsAdapter(private val context: Context) :
    PagingDataAdapter<Item, RecyclerView.ViewHolder>(DiffUtilCallBack()) {
    private var userHomeVideoAdapter: UserHomeVideoAdapter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            Constants.HOME_SECTION_FIRST_TABS -> {
                val binding: HomeFirstTabsBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.home_first_tabs,
                    parent,
                    false
                )
                return FirstTabsViewHolder(context, binding)
            }
            Constants.HOME_SECTION_SECOND_TABS -> {
                val binding: UserHomeSecondTabsBinding =
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.user_home_second_tabs,
                        parent,
                        false
                    )
                return SecondTabsViewHolder(context, binding)
            }
            Constants.USER_HOME_SECTION_BANNER -> {
                val binding: UserHomeBannerHolderBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.user_home_banner_holder,
                    parent,
                    false
                )
                return UserHomePosterViewHolder(context, binding)
            }
            Constants.USER_HOME_SECTION_POPULAR_CONSULTATIONS -> {
                val binding: UserHomeConsulationsHolderBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.user_home_consulations_holder,
                    parent,
                    false
                )
                return ServiceConsultationViewHolder(context, binding)
            }
            Constants.USER_HOME_SECTION_POPULAR_CLASSES -> {
                val binding: UserHomeClassesHolderBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.user_home_classes_holder,
                    parent,
                    false
                )
                return ServiceClassesViewHolder(context, binding)
            }
            Constants.USER_HOME_SECTION_PENDING_BOOKINGS -> {
                val binding: UserHomeBookingHolderBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.user_home_booking_holder,
                    parent,
                    false
                )
                return UserHomeBookingViewHolder(context, binding)
            }
            Constants.USER_HOME_SECTION_SERVICE_LISTINGS -> {
                val binding: UserHomeServiceHolderBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.user_home_service_holder,
                    parent,
                    false
                )
                return UserHomeServiceViewHolder(context, binding)
            }
            Constants.USER_HOME_SECTION_YOUR_CONSULTANTS -> {
                val binding: UserHomeConsultantsHolderBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.user_home_consultants_holder,
                    parent,
                    false
                )
                return UserHomeConsultantsViewHolder(context, binding)
            }
            Constants.USER_HOME_SECTION_DISCOVER_EXPERTS_VIDEO -> {
                val binding: UserHomeDiscoverExpertHolderBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.user_home_discover_expert_holder,
                    parent,
                    false
                )
                return DiscoverExpertViewHolder(context, binding)
            }
            Constants.HOME_SECTION_TITLE -> {
                val binding: UserHomeSectionTitleHolderBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.user_home_section_title_holder,
                    parent,
                    false
                )
                return SectionTitleViewHolder(context, binding)
            }
            Constants.HOME_SECTION_SHOW_MORE -> {
                val binding: UserHomeShowMoreHolderBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.user_home_show_more_holder,
                    parent,
                    false
                )
                return BrowseAllViewHolder(context, binding)
            }
        }
        val binding: ItemProgressbarBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_progressbar,
                parent,
                false
            )
        return LoadingViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        val response = getItem(position)
        return if (response != null) {
            response.section_id!!
        } else {
            Constants.VIEW_TYPE_LOADING
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val response: Any? = getItem(position)
        var list: Any? = null
        Timber.d("Inside onBindViewHolder : " + response)
        if (response is HomeFeedModel) {
            Timber.d("Inside onBindViewHolder response is home model")
            list = response.items
        }
        Timber.d("Inside onBindViewHolder item type : " + holder.itemViewType)

        if (response != null) {
            when (holder.itemViewType) {
                Constants.USER_HOME_SECTION_BANNER -> {
                    val posterViewHolder = holder as UserHomePosterViewHolder
                    posterViewHolder.bindData(list)
                }
                Constants.USER_HOME_SECTION_SERVICE_LISTINGS -> {
                    val serviceViewHolder = holder as UserHomeServiceViewHolder
                    serviceViewHolder.bindData(list)
                }
                Constants.USER_HOME_SECTION_PENDING_BOOKINGS -> {
                    val bookingViewHolder = holder as UserHomeBookingViewHolder
                    bookingViewHolder.bindData(list)
                }
                Constants.USER_HOME_SECTION_YOUR_CONSULTANTS -> {
                    val userHomeConsultantsViewHolder = holder as UserHomeConsultantsViewHolder
                    userHomeConsultantsViewHolder.bindData(list)
                }
                Constants.USER_HOME_SECTION_DISCOVER_EXPERTS_VIDEO -> {
                    val discoverExpertViewHolder = holder as DiscoverExpertViewHolder
                    discoverExpertViewHolder.bindData(list)
                }
                Constants.USER_HOME_SECTION_POPULAR_CONSULTATIONS -> {
                    val consultationViewHolder = holder as ServiceConsultationViewHolder
                    consultationViewHolder.bindData(list)
                }
                Constants.USER_HOME_SECTION_POPULAR_CLASSES -> {
                    val classesViewHolder = holder as ServiceClassesViewHolder
                    classesViewHolder.bindData(list)
                }
                Constants.HOME_SECTION_FIRST_TABS -> {
                    val firstTabsViewHolder = holder as FirstTabsViewHolder
                    firstTabsViewHolder.bindData(list)
                }
                Constants.HOME_SECTION_SECOND_TABS -> {
                    val secondTabsViewHolder = holder as SecondTabsViewHolder
                    secondTabsViewHolder.bindData(list)
                }
                Constants.HOME_SECTION_TITLE -> {
                    if (response is HomeItemTitle) {
                        val homeItemTitle = response as HomeItemTitle
                        val showTitleViewHolder = holder as SectionTitleViewHolder
                        showTitleViewHolder.bindData(homeItemTitle)

                    }
                }
                Constants.HOME_SECTION_SHOW_MORE -> {
                    if (response is BrowseAll) {
                        val browseAll = response as BrowseAll
                        val showMoreViewHolder = holder as BrowseAllViewHolder
                        showMoreViewHolder.bindData(browseAll)
                    }
                }

            }
        } else {
            val loadingViewHolder = holder as LoadingViewHolder
            loadingViewHolder.bindData()
        }
    }

    inner class UserHomePosterViewHolder internal constructor(
        private val context: Context,
        private val binding: UserHomeBannerHolderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(result: Any?) {
            val gson = Gson()
            val json = gson.toJson(result)
            val itemType = object : TypeToken<List<Banner>>() {}.type
            val items = gson.fromJson<List<Banner>>(json, itemType)
            val homeBookingAdapter: UserBannerAdapter = UserBannerAdapter(context, items.toMutableList())
            binding.rvBanner.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.rvBanner.adapter = homeBookingAdapter
            Timber.d("inside UserHomePosterViewHolder   " + items?.size)
        }
    }

    inner class UserHomeServiceViewHolder internal constructor(
        private val context: Context,
        private val binding: UserHomeServiceHolderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(result: Any?) {
            val gson = Gson()
            val json = gson.toJson(result)
            val itemType = object : TypeToken<List<ExpertServicesResponse>>() {}.type
            val items = gson.fromJson<List<ExpertServicesResponse>>(json, itemType)
            val homeBookingAdapter: UserHomeServiceAdapter = UserHomeServiceAdapter(context, items)
            binding.rvServices.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvServices.adapter = homeBookingAdapter
            Timber.d("inside UserHomeServiceViewHolder   " + items?.size)
        }
    }

    inner class UserHomeBookingViewHolder internal constructor(
        private val context: Context,
        private val binding: UserHomeBookingHolderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(result: Any?) {
            val gson = Gson()
            val json = gson.toJson(result)
            val itemType = object : TypeToken<List<ExpertServicesResponse>>() {}.type
            val items = gson.fromJson<List<ExpertServicesResponse>>(json, itemType)
            val homeBookingAdapter: UserHomeServiceAdapter = UserHomeServiceAdapter(context, items)
            binding.rvUpcomingBooking.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvUpcomingBooking.adapter = homeBookingAdapter

            Timber.d("inside UserHomeBookingViewHolder   " + items?.size)

        }
    }

    inner class UserHomeConsultantsViewHolder internal constructor(
        private val context: Context,
        private val binding: UserHomeConsultantsHolderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(result: Any?) {
            val gson = Gson()
            val json = gson.toJson(result)
            val itemType = object : TypeToken<List<Expert>>() {}.type
            val items = gson.fromJson<List<Expert>>(json, itemType)
            val homeClientsAdapter = UserHomeConsultantsAdapter(context, items)
            binding.rvConsultants.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvConsultants.adapter = homeClientsAdapter
            Timber.d("inside UserHomeConsultantsViewHolder   " + items?.size)
        }
    }

    inner class DiscoverExpertViewHolder internal constructor(
        private val context: Context,
        private val binding: UserHomeDiscoverExpertHolderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(result: Any?) {
            val gson = Gson()
            val json = gson.toJson(result)
            val itemType = object : TypeToken<List<Item>>() {}.type
            val items = gson.fromJson<List<Item>>(json, itemType)
            userHomeVideoAdapter = UserHomeVideoAdapter(context, items)
            binding.rvDiscoverExpert.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.rvDiscoverExpert.adapter = userHomeVideoAdapter

            Timber.d("inside UserHomeDiscover   " + items?.size)

        }
    }

    fun updateDiscoverHomeItem(item: Item?, position: Int) {
        userHomeVideoAdapter?.notifyItemChanged(position, item)
    }

    inner class ServiceConsultationViewHolder internal constructor(
        private val context: Context,
        private val binding: UserHomeConsulationsHolderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(result: Any?) {
            val gson = Gson()
            val json = gson.toJson(result)
            val itemType = object : TypeToken<List<CategoryModelServices>>() {}.type
            val items = gson.fromJson<List<CategoryModelServices>>(json, itemType)
            val popularConsultationAdapter = PopularConsultationAdapter(context, items)
            binding.rvConsultations.layoutManager =
                StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
            binding.rvConsultations.adapter = popularConsultationAdapter

            Timber.d("inside UserHomeServiceConsultationwHolder   " + items?.size)

        }
    }

    inner class ServiceClassesViewHolder internal constructor(
        private val context: Context,
        private val binding: UserHomeClassesHolderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(result: Any?) {
            val gson = Gson()
            val json = gson.toJson(result)
            val itemType = object : TypeToken<List<CategoryModelServices>>() {}.type
            val items = gson.fromJson<List<CategoryModelServices>>(json, itemType)
            val popularClassesAdapter = PopularClassesAdapter(context, items)
            binding.rvClasses.layoutManager =
                StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
            binding.rvClasses.adapter = popularClassesAdapter

            Timber.d("inside UserHomeServiceClasseswHolder   " + items?.size)
        }
    }

    inner class FirstTabsViewHolder internal constructor(
        private val context: Context,
        private val binding: HomeFirstTabsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(result: Any?) {
            val items = getListOfTabsFromObject(result)
            val homeTabsAdapter =
                UserHomeTabsAdapter(context, items as MutableList<HomeTabsModel?>?)
            binding.rvFirstTabs.layoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            binding.rvFirstTabs.adapter = homeTabsAdapter

            Timber.d("inside UserHomeBrowseAllswHolder   " + items?.size)
        }
    }

    inner class SecondTabsViewHolder internal constructor(
        private val context: Context,
        private val binding: UserHomeSecondTabsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(result: Any?) {
            val items = getListOfTabsFromObject(result)
            val homeTabsMoreAdapter = UserHomeMoreTabsAdapter(context, items)
            binding.rvSecondTabs.layoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            binding.rvSecondTabs.adapter = homeTabsMoreAdapter
            Timber.d("inside UserHomeBrowseAllswHolder   " + items?.size)
        }
    }

    inner class BrowseAllViewHolder internal constructor(
        private val context: Context,
        private val binding: UserHomeShowMoreHolderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(browseAll: BrowseAll) {
            binding.tvBrowseAll.text = browseAll.title
            Timber.d("inside UserHomeBrowseAllswHolder   ")
        }
    }

    inner class SectionTitleViewHolder internal constructor(
        private val context: Context,
        private val binding: UserHomeSectionTitleHolderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(homeItemTitle: HomeItemTitle) {
            binding.tvTitle.text = homeItemTitle.title
            if (homeItemTitle.isShowAll == true) {
                binding.tvViewAll.visibility = View.VISIBLE
            } else {
                binding.tvViewAll.visibility = View.GONE
            }
            Timber.d("inside UserHomeSectionTitleswHolder")
        }
    }

    private fun getListOfTabsFromObject(objectList: Any?): List<HomeTabsModel>? {
        val servicesResponses: MutableList<HomeTabsModel> = java.util.ArrayList()
        if (objectList is List<*>) {
            for (`object` in objectList) {
                if (`object` is HomeTabsModel) {
                    servicesResponses.add(`object`)
                }
            }
        }
        return servicesResponses
    }
}