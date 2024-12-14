package com.rarmash.prac5

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Cart::class], version = 1)
@TypeConverters(Converters::class)
abstract class CartsDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}
