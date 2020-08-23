package com.vikas.scatteredgriddemo.model

data class Data(
    val filters: List<Filter>?,
    val info_badge: String?,
    val info_message: String?,
    val products: List<Product>?,
    val title: String?
)