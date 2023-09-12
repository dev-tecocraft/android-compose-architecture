package com.teco.apparchitecure.data.remote.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Self(
    @SerializedName("href")
    val href: String = ""
)