package com.teco.apparchitecure.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teco.apparchitecure.data.remote.service.AppNetworkRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appNetworkRepository: AppNetworkRepositoryImpl
) : ViewModel() {

    private var _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = _homeScreenState.asStateFlow()

    init {
        callApi()
    }

    private fun callApi() {
        viewModelScope.launch {
            _homeScreenState.update {
                _homeScreenState.value.copy(
                    isLoading = true
                )
            }
            appNetworkRepository.getCategories()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _homeScreenState.update {
                        _homeScreenState.value.copy(
                            isLoading = false,
                            errorMessage = e.localizedMessage ?: "Something went wrong.",
                            categoryList = null
                        )
                    }
                }
                .collect { categoryResponseModelList ->
                    val allCategoryList = categoryResponseModelList.map { it.toCategoryModel() }
                    val parentCategories = allCategoryList.filter { it.parent == 0 }
                    for (category in parentCategories) {
                        val childList = allCategoryList.filter { it.parent == category.id }
                        category.childCategory = ArrayList(childList)
                    }
                    _homeScreenState.update {
                        _homeScreenState.value.copy(
                            isLoading = false,
                            errorMessage = null,
                            categoryList = parentCategories
                        )
                    }
                }
        }
    }
}