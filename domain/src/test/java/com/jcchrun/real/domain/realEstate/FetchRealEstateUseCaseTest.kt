package com.jcchrun.real.domain.realEstate

import com.jcchrun.real.models.Output
import com.jcchrun.real.models.RealEstateList
import com.jcchrun.real.repositories.repo.RealEstateRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FetchRealEstateUseCaseTest {

    @MockK
    lateinit var mockRealEstateRepository: RealEstateRepository

    @MockK
    lateinit var mockOutput: Output<RealEstateList>

    private lateinit var useCase: FetchRealEstateListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = FetchRealEstateListUseCase(
            mockRealEstateRepository
        )
    }

    @Test
    fun `test execute`() {
        coEvery { mockRealEstateRepository.getRealEstateList() } returns mockOutput
        val output = runBlocking {
            useCase.execute()
        }
        coVerify { mockRealEstateRepository.getRealEstateList() }
        Assert.assertEquals(mockOutput, output)
    }
}