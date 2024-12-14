package com.rarmash.prac5

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cart(
    @PrimaryKey val id: Int,
    val products: List<String>,
    val total: Double,
    val discountedTotal: Double,
    val userId: Int,
    val totalProducts: Int,
    val totalQuantity: Int
)
