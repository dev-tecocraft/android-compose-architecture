package com.teco.apparchitecure.data.remote.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.teco.apparchitecure.data.local.model.CategoryModel

@Keep
data class CategoryResponseModel(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("display")
    val display: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image")
    val image: Image = Image(),
    @SerializedName("_links")
    val links: Links = Links(),
    @SerializedName("menu_order")
    val menuOrder: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("parent")
    val parent: Int = 0,
    @SerializedName("slug")
    val slug: String = ""
){
    fun toCategoryModel() = CategoryModel(
        id = id,
        parent = parent,
        name = name
    )
}