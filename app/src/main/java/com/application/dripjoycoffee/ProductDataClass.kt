package com.application.dripjoycoffee

import java.io.Serializable

data class ProductDataClass(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    //val slug: String = "",
    //val gallery_images: String,
    val media_images: String = "",
    val galleries: List<String> = listOf(),
): Serializable



