package com.bendingbytes.shoes.di

import com.bendingbytes.shoes.network.ShoeNetworkMapper
import com.bendingbytes.shoes.network.ShoeService
import com.bendingbytes.shoes.repository.ShoesRepository
import com.bendingbytes.shoes.room.ShoeCacheMapper
import com.bendingbytes.shoes.room.ShoeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        shoeNetworkMapper: ShoeNetworkMapper,
        shoeCacheMapper: ShoeCacheMapper,
        shoeService: ShoeService,
        shoeDao: ShoeDao
    ): ShoesRepository {
        return ShoesRepository(
            shoeNetworkMapper,
            shoeCacheMapper,
            shoeService,
            shoeDao
        )
    }
}