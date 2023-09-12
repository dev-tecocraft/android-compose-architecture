package com.teco.apparchitecure.data.remote.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Links(
    @SerializedName("collection")
    val collection: List<Collection> = listOf(),
    @SerializedName("self")
    val self: List<Self> = listOf()
)