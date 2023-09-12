package com.teco.apparchitecure.data.remote.service

import com.teco.apparchitecure.data.remote.model.CategoryResponseModel
import retrofit2.http.GET

interface AppApiService {

    @GET("test_category.json")
    suspend fun getCategories() : List<CategoryResponseModel>
}