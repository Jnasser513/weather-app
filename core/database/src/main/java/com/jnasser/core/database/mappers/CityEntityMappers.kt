package com.jnasser.core.database.mappers

import com.jnasser.core.database.entity.CityEntity
import com.jnasser.core.domain.city.City

fun CityEntity.toDomain() = City(
    id = id,
    place = place,
    lat = lat,
    lon = lon
)

fun City.toEntity() = CityEntity(
    id = id,
    place = place,
    lat = lat,
    lon = lon
)