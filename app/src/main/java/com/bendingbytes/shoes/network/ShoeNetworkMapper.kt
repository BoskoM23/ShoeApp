package com.bendingbytes.shoes.network

import com.bendingbytes.shoes.domain.Shoe
import javax.inject.Inject

class ShoeNetworkMapper
@Inject constructor() : EntityMapper<ShoeNetworkEntity, Shoe> {
    override fun mapFromEntity(entity: ShoeNetworkEntity): Shoe {
        return Shoe(
            id = entity.id,
            name = entity.name,
            price = entity.price,
            image = entity.image,
            description = entity.description
        )
    }

    override fun mapToEntity(domainModel: Shoe): ShoeNetworkEntity {
        return ShoeNetworkEntity(
            id = domainModel.id,
            name = domainModel.name,
            price = domainModel.price,
            image = domainModel.image,
            description = domainModel.description
        )
    }

    fun mapFromListEntity(list: List<ShoeNetworkEntity>): List<Shoe> {
        return list.map { mapFromEntity(it) }
    }
}