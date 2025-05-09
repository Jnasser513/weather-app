package com.jnasser.core.domain.repositories

interface WindRepository {
    fun getLocalizedWindDescription(windSpeed: Double): String
}