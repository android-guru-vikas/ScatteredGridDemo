package com.vikas.scatteredgriddemo.model

data class Product(
    val product_inventory: ProductInventory?,
    val product_master: ProductMaster?,
    val product_merchantdising: ProductMerchantdising?,
    val product_pricing: ProductPricing?
)