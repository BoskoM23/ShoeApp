package com.bendingbytes.shoes.network

interface EntityMapper<Entity, DomainEntity> {
    fun mapFromEntity(entity: Entity): DomainEntity

    fun mapToEntity(domainModel: DomainEntity): Entity
}