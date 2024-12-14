package com.rarmash.prac5

import androidx.lifecycle.LiveData
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartApi: CartApi, private val cartDao: CartDao) {

    suspend fun fetchAndSaveCarts() {
        val carts = cartApi.getCarts(3).carts
        cartDao.insertCarts(carts)
    }

    fun getAllCarts(): LiveData<List<Cart>> {
        return cartDao.getAllCarts()
    }
}
