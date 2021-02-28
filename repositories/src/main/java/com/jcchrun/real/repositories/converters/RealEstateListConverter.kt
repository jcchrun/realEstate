package com.jcchrun.real.repositories.converters

import com.jcchrun.real.models.RealEstateList
import com.jcchrun.real.remote.dto.RealEstateListDto

class RealEstateListConverter(
    private val realEstateItemConverter: RealEstateItemConverter
) {

    fun convertDtoToModel(realEstateListDto: RealEstateListDto): RealEstateList {
        return RealEstateList(
            realEstateListDto.realEstateItemDtoList.map { realEstateItemDto ->
                realEstateItemConverter.convertDtoToModel(
                    realEstateItemDto
                )
            },
            realEstateListDto.totalCount
        )
    }
}