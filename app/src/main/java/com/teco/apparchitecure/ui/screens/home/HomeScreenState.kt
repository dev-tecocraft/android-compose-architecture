package com.teco.apparchitecure.ui.screens.home

import com.teco.apparchitecure.data.local.model.CategoryModel

data class HomeScreenState(
    val isLoading: Boolean? = null,
    val categoryList: List<CategoryModel>? = null,
    val errorMessage: String? = null
)
