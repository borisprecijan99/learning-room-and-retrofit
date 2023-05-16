package com.example.learningroomandretrofit.data

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("author")
    val author: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("quote")
    val quote: String
)