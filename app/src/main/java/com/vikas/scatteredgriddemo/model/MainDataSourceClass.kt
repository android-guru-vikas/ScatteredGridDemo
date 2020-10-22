package com.vikas.scatteredgriddemo.model

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import com.vikas.scatteredgriddemo.backend.NetworkService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MainDataSourceClass @Inject constructor(private val mainApi: NetworkService) :
    PagingSource<Int, Item>() {

    private val initialPageIndex: Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val position = params.key ?: initialPageIndex
        return try {
            val response = mainApi.fetchUserHomeResponse("en", position).await()
            Page(
                data = response.items as List<Item>,
                prevKey = /*if (position == initialPageIndex) null else position - */null,
                nextKey = /*if (response.items?.isEmpty()!!) null else position + 1*/ null
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}