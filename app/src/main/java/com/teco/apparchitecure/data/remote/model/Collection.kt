package com.teco.apparchitecure.data.remote.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Collection(
    @SerializedName("href")
    val href: String = ""
)