package com.bendingbytes.shoes.room

import com.bendingbytes.shoes.domain.Shoe
import com.bendingbytes.shoes.network.EntityMapper
import javax.inject.Inject

class ShoeCacheMapper
@Inject
constructor() : EntityMapper<ShoeCacheEntity, Shoe> {
    override fun mapFromEntity(entity: ShoeCacheEntity): Shoe {
        return Shoe(
            id = entity.id,
            name = entity.name,
            price = entity.price,
            image = entity.image,
            description = entity.description
        )
    }

    override fun mapToEntity(domainModel: Shoe): ShoeCacheEntity {
        return ShoeCacheEntity(
            id = domainModel.id,
            name = domainModel.name,
            price = domainModel.price,
            image = domainModel.image,
            description = domainModel.description
        )
    }

    fun mapToListEntity(list: List<Shoe>): List<ShoeCacheEntity> {
        return list.map { mapToEntity(it) }
    }

    fun mapFromListEntity(list: List<ShoeCacheEntity>): List<Shoe> {
        return list.map { mapFromEntity(it) }
    }
}