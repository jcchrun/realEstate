package com.jcchrun.real.repositories.converters

import com.jcchrun.real.models.RealEstateItem
import com.jcchrun.real.remote.dto.RealEstateItemDto
import com.jcchrun.real.remote.dto.RealEstateListDto
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RealEstateListConverterTest {

    @MockK
    lateinit var mockRealEstateItemConverter: RealEstateItemConverter

    @MockK
    lateinit var mockRealEstateItemDto: RealEstateItemDto

    @MockK
    lateinit var mockRealEstateItem: RealEstateItem

    private lateinit var converter: RealEstateListConverter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        converter = RealEstateListConverter(mockRealEstateItemConverter)
    }

    @Test
    fun `test convertDtoToModel`() {
        val dto = RealEstateListDto(
            listOf(mockRealEstateItemDto),
            12
        )
        coEvery { mockRealEstateItemConverter.convertDtoToModel(any()) } returns mockRealEstateItem
        val model = converter.convertDtoToModel(dto)
        Assert.assertEquals(listOf(mockRealEstateItem), model.items)
        Assert.assertEquals(12, model.count)
        coVerify { mockRealEstateItemConverter.convertDtoToModel(mockRealEstateItemDto) }
    }
}