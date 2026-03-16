package com.jnasser.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_entity")
data class CityEntity(
    @PrimaryKey val id: String,
    val place: String,
    val lat: Double,
    val lon: Double
)
