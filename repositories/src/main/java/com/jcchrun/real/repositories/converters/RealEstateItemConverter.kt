package com.jcchrun.real.repositories.converters

import com.jcchrun.real.models.RealEstateItem
import com.jcchrun.real.remote.dto.RealEstateItemDto

class RealEstateItemConverter {

    fun convertDtoToModel(realEstateItemDto: RealEstateItemDto): RealEstateItem {
        return RealEstateItem(
            realEstateItemDto.id,
            realEstateItemDto.bedrooms,
            realEstateItemDto.rooms,
            realEstateItemDto.city,
            realEstateItemDto.area,
            realEstateItemDto.imageUrl ?: "",
            realEstateItemDto.price,
            realEstateItemDto.professional,
            realEstateItemDto.propertyType
        )
    }
}