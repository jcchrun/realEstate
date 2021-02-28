package com.jcchrun.real.remote.dto

import com.jcchrun.real.remote.AbstractRemoteTest
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.junit.Assert
import org.junit.Test

class RealEstateListDtoTest: AbstractRemoteTest() {

    @Test
    fun `test parse from file`() {
        val jsonString = readFile("real_estate_list.json")
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<RealEstateListDto> = moshi.adapter(RealEstateListDto::class.java)
        val realEstateListDto = adapter.fromJson(jsonString)
        //
        Assert.assertEquals(4, realEstateListDto?.totalCount)
        Assert.assertEquals(4, realEstateListDto?.realEstateItemDtoList?.size)
        //
        val firstItemDto = realEstateListDto?.realEstateItemDtoList?.get(0)
        Assert.assertEquals(1, firstItemDto?.id)
        Assert.assertEquals(4, firstItemDto?.bedrooms)
        Assert.assertEquals("Villers-sur-Mer", firstItemDto?.city)
        Assert.assertEquals(250.0, firstItemDto?.area)
        Assert.assertEquals("https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg", firstItemDto?.imageUrl)
        Assert.assertEquals(1500000.0, firstItemDto?.price)
        Assert.assertEquals("GSL EXPLORE", firstItemDto?.professional)
        Assert.assertEquals("Maison - Villa", firstItemDto?.propertyType)
        Assert.assertEquals(8, firstItemDto?.rooms)
    }
}