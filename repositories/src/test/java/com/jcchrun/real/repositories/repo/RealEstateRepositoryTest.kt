package com.jcchrun.real.repositories.repo

import android.accounts.NetworkErrorException
import com.jcchrun.real.commons.app.helpers.NetworkHelper
import com.jcchrun.real.models.Output
import com.jcchrun.real.models.RealEstateList
import com.jcchrun.real.remote.api.ApiServices
import com.jcchrun.real.remote.dto.RealEstateListDto
import com.jcchrun.real.repositories.converters.RealEstateListConverter
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.IllegalArgumentException

class RealEstateRepositoryTest {

    @MockK
    lateinit var mockApiServices: ApiServices

    @MockK
    lateinit var mockRealEstateListConverter: RealEstateListConverter

    @MockK
    lateinit var mockNetworkHelper: NetworkHelper

    @MockK
    lateinit var mockRealEstateListDto: RealEstateListDto

    @MockK
    lateinit var mockRealEstateList: RealEstateList

    private lateinit var repository: RealEstateRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = RealEstateRepositoryImpl(
            mockApiServices,
            mockRealEstateListConverter,
            mockNetworkHelper
        )
    }

    @Test
    fun `test getRealEstateList success`() {
        coEvery { mockApiServices.fetchRealEstateList() } returns mockRealEstateListDto
        coEvery { mockRealEstateListConverter.convertDtoToModel(any()) } returns mockRealEstateList
        val output = runBlocking {
            repository.getRealEstateList()
        }
        Assert.assertTrue(output is Output.Success)
        Assert.assertEquals(mockRealEstateList, (output as Output.Success).result)
    }

    @Test
    fun `test getRealEstateList error no network`() {
        val exception = NetworkErrorException()
        coEvery { mockApiServices.fetchRealEstateList() } throws exception
        coEvery { mockNetworkHelper.isConnected() } returns false
        val output = runBlocking {
            repository.getRealEstateList()
        }
        Assert.assertTrue(output is Output.Error)
        Assert.assertEquals(Output.Error.ERROR_CODE_NO_NETWORK, (output as Output.Error).errorCode)
        Assert.assertEquals("", (output as Output.Error).errorResponse)
        Assert.assertEquals(exception, (output as Output.Error).exception)
    }

    @Test
    fun `test getRealEstateList error unknown`() {
        val exception = IllegalArgumentException()
        coEvery { mockApiServices.fetchRealEstateList() } throws exception
        coEvery { mockNetworkHelper.isConnected() } returns true
        val output = runBlocking {
            repository.getRealEstateList()
        }
        Assert.assertTrue(output is Output.Error)
        Assert.assertEquals(Output.Error.ERROR_CODE_UNKNOWN, (output as Output.Error).errorCode)
        Assert.assertEquals("", (output as Output.Error).errorResponse)
        Assert.assertEquals(exception, (output as Output.Error).exception)
    }
}