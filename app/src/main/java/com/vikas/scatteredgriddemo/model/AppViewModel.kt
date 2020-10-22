package com.vikas.scatteredgriddemo.model

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vikas.scatteredgriddemo.utils.Constants.perPage
import kotlinx.coroutines.flow.Flow

class AppViewModel @ViewModelInject constructor(private val mainDataSourceClass: MainDataSourceClass) : ViewModel() {

    private var currentResult: Flow<PagingData<Item>>? = null

    fun imageData(): Flow<PagingData<Item>> {
        val lastResult = currentResult
        if (lastResult != null) return lastResult

        val newResult: Flow<PagingData<Item>> = Pager(
            PagingConfig(pageSize = perPage),
            pagingSourceFactory = { mainDataSourceClass }).flow.cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }
}