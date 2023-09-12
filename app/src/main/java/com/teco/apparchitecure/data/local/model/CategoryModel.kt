package com.teco.apparchitecure.data.local.model

data class CategoryModel(
    val id: Int,
    val parent: Int,
    val name: String,
){
    var childCategory: ArrayList<CategoryModel> = ArrayList()
}
