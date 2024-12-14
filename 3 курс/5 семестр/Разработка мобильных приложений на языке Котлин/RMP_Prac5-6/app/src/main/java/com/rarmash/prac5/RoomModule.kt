package com.rarmash.prac5

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun providesCartsDb(app: Application): CartsDatabase{
        return Room.databaseBuilder(
            app,
            CartsDatabase::class.java, "cart-database"
        ).build()
    }

    @Provides
    fun provideCartDao(database: CartsDatabase): CartDao {
        return database.cartDao()
    }
}