package com.application.dripjoycoffee

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("products") val products: List<ProductDataClass>
)
