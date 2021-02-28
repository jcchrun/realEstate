package com.jcchrun.real.remote.api

import com.jcchrun.real.remote.dto.RealEstateListDto
import retrofit2.http.GET

interface ApiServices {

    @GET("/listings.json")
    suspend fun fetchRealEstateList(): RealEstateListDto
}