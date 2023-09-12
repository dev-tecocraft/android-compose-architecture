package com.teco.apparchitecure.data.remote.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Image(
    @SerializedName("alt")
    val alt: String = "",
    @SerializedName("date_created")
    val dateCreated: String = "",
    @SerializedName("date_modified")
    val dateModified: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("src")
    val src: String = "",
    @SerializedName("title")
    val title: String = ""
)