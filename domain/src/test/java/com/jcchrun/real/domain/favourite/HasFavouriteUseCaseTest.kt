package com.jcchrun.real.domain.favourite

import com.jcchrun.real.repositories.repo.FavouriteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class HasFavouriteUseCaseTest {

    @MockK
    lateinit var mockFavouriteRepository: FavouriteRepository

    private lateinit var useCase: HasFavouriteUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = HasFavouriteUseCase(
            mockFavouriteRepository
        )
    }

    @Test
    fun `test execute`() {
        val realEstateId = 12
        coEvery { mockFavouriteRepository.getFavourite(any()) } returns true
        val hasFavourite = runBlocking {
            useCase.execute(realEstateId)
        }
        coVerify { mockFavouriteRepository.getFavourite(realEstateId) }
        Assert.assertTrue(hasFavourite)
    }
}