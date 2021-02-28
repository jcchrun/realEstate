package com.jcchrun.real.remote.dto

import com.squareup.moshi.Json

data class RealEstateListDto(
    @field:Json(name = "items") val realEstateItemDtoList: List<RealEstateItemDto>,
    @field:Json(name = "totalCount") val totalCount: Int
) {
}