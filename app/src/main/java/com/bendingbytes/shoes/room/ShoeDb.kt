package com.bendingbytes.shoes.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShoeCacheEntity::class], version = 1)
abstract class ShoeDb : RoomDatabase() {
    abstract fun shoeDao(): ShoeDao

    companion object {
        val DATABASE_NAME = "Shoe_data"
    }
}