package com.jcchrun.real.remote.dto

import com.squareup.moshi.Json

data class RealEstateItemDto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "bedrooms") val bedrooms: Int,
    @field:Json(name = "city") val city: String,
    @field:Json(name = "area") val area: Double,
    @field:Json(name = "url") val imageUrl: String? = null,
    @field:Json(name = "price") val price: Double,
    @field:Json(name = "professional") val professional: String,
    @field:Json(name = "propertyType") val propertyType: String,
    @field:Json(name = "rooms") val rooms: Int
) {
}