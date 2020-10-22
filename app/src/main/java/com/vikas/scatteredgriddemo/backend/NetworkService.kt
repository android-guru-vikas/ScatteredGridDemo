package com.vikas.scatteredgriddemo.backend

import com.vikas.scatteredgriddemo.model.ExpertServicesResponse
import com.vikas.scatteredgriddemo.model.HomeFeedModel
import com.vikas.scatteredgriddemo.utils.Constants
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET(Constants.API_USER_HOME)
    fun fetchUserHomeResponse(@Query("language__in") lang: String?, @Query("page") page: Int): Deferred<HomeFeedModel>

    @GET(Constants.API_GET_ALL_EXPERT_SERVICES)
    fun getAllExpertsServices(@Query("page") page: Int, @Query("language") lang: String?): Deferred<ArrayList<ExpertServicesResponse>>
}