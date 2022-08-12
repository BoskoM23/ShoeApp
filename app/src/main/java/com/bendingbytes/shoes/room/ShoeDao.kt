package com.bendingbytes.shoes.room

import androidx.room.*

@Dao
interface ShoeDao {

    @Query("SELECT * FROM shoes_entity")
    fun getAll(): List<ShoeCacheEntity>

    @Query("SELECT * FROM shoes_entity WHERE id = :shoeId")
    fun findById(shoeId: Int): ShoeCacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shoe: ShoeCacheEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(shoes: List<ShoeCacheEntity>)

    @Delete
    fun delete(shoe: ShoeCacheEntity)

    @Query("DELETE FROM shoes_entity")
    fun deleteAll()
}