package com.bendingbytes.shoes.di

import android.content.Context
import androidx.room.Room
import com.bendingbytes.shoes.room.ShoeDao
import com.bendingbytes.shoes.room.ShoeDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): ShoeDb {
        return Room.databaseBuilder(
            appContext,
            ShoeDb::class.java,
            "Shoe_data"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoeDao(shoeDb: ShoeDb): ShoeDao {
        return shoeDb.shoeDao()
    }
}