package com.teco.apparchitecure.data.remote.service

import com.teco.apparchitecure.data.remote.model.CategoryResponseModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppNetworkRepositoryImpl @Inject constructor(
    private val apiService: AppApiService
) : AppNetworkRepository {
    override fun getCategories(): Flow<List<CategoryResponseModel>> = flow {
        emit(apiService.getCategories())
    }
}