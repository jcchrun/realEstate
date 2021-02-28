package com.jcchrun.real.repositories.converters

import com.jcchrun.real.remote.dto.RealEstateItemDto
import org.junit.Assert
import org.junit.Test

class RealEstateItemConverterTest {

    private val converter = RealEstateItemConverter()

    @Test
    fun `test convertDtoToModel`() {
        val dto = RealEstateItemDto(
            12,
            23,
            "city",
            34.0,
            "imageUrl",
            45.0,
            "professional",
            "propertyType",
            56
        )
        val model = converter.convertDtoToModel(dto)
        Assert.assertEquals(12, model.id)
        Assert.assertEquals(23, model.bedrooms)
        Assert.assertEquals("city", model.city)
        Assert.assertEquals(34.0, model.area, 0.0)
        Assert.assertEquals("imageUrl", model.imageUrl)
        Assert.assertEquals(45.0, model.price, 0.0)
        Assert.assertEquals("professional", model.professional)
        Assert.assertEquals("propertyType", model.propertyType)
        Assert.assertEquals(56, model.rooms)
    }

    @Test
    fun `test convertDtoToModel with null imageUrl`() {
        val dto = RealEstateItemDto(
            12,
            23,
            "city",
            34.0,
            null,
            45.0,
            "professional",
            "propertyType",
            56
        )
        val model = converter.convertDtoToModel(dto)
        Assert.assertEquals(12, model.id)
        Assert.assertEquals(23, model.bedrooms)
        Assert.assertEquals("city", model.city)
        Assert.assertEquals(34.0, model.area, 0.0)
        Assert.assertEquals("", model.imageUrl)
        Assert.assertEquals(45.0, model.price, 0.0)
        Assert.assertEquals("professional", model.professional)
        Assert.assertEquals("propertyType", model.propertyType)
        Assert.assertEquals(56, model.rooms)
    }
}