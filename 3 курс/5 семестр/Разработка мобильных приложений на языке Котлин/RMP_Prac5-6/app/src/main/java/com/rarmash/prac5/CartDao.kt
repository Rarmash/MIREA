package com.rarmash.prac5

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCarts(carts: List<Cart>)

    @Query("SELECT * FROM cart")
    fun getAllCarts(): LiveData<List<Cart>>
}
