package com.bendingbytes.shoes.repository

import com.bendingbytes.shoes.domain.DataState
import com.bendingbytes.shoes.domain.Shoe
import com.bendingbytes.shoes.network.ShoeNetworkMapper
import com.bendingbytes.shoes.network.ShoeService
import com.bendingbytes.shoes.room.ShoeCacheMapper
import com.bendingbytes.shoes.room.ShoeDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class ShoesRepository
@Inject
constructor(
    private val shoeNetworkMapper: ShoeNetworkMapper,
    private val shoeCacheMapper: ShoeCacheMapper,
    private val shoeService: ShoeService,
    private val shoeDao: ShoeDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getShoes(): Flow<DataState<List<Shoe>>> = flow {
        emit(DataState.Loading)

        val listShoeNetworkEntity = shoeService.getShoes()
        Timber.d(listShoeNetworkEntity.toString())
        val shoes = shoeNetworkMapper.mapFromListEntity(listShoeNetworkEntity)
        shoeDao.insertAll(shoeCacheMapper.mapToListEntity(shoes))
        val shoesCacheEntities = shoeDao.getAll()
        val shoeList = shoeCacheMapper.mapFromListEntity(shoesCacheEntities)

        emit(DataState.Success(shoeList))
    }.flowOn(ioDispatcher).catch {
        Timber.e(it)
        emit(DataState.Error(it))
    }

    suspend fun loadShoesFromDB(): Flow<DataState<List<Shoe>>> = flow {
        val shoesCacheEntities = shoeDao.getAll()
        val shoesDb = shoeCacheMapper.mapFromListEntity(shoesCacheEntities)
        emit(DataState.Success(shoesDb))
    }.flowOn(ioDispatcher)

    suspend fun getShoeForId(id: Int): Flow<DataState<Shoe>> = flow {
        emit(DataState.Loading)
        val shoeCacheEntity = shoeDao.findById(id)
        val shoe = shoeCacheMapper.mapFromEntity(shoeCacheEntity)
        emit(DataState.Success(shoe))
    }.flowOn(ioDispatcher)
}