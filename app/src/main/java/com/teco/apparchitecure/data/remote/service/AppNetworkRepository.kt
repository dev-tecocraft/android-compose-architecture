package com.teco.apparchitecure.data.remote.service

import com.teco.apparchitecure.data.remote.model.CategoryResponseModel
import kotlinx.coroutines.flow.Flow

interface AppNetworkRepository {
    fun getCategories(): Flow<List<CategoryResponseModel>>
}