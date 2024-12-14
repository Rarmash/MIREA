package com.rarmash.prac5

data class CartResponse(
    val carts: List<Cart>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
