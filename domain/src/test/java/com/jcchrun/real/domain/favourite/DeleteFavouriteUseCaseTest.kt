package com.jcchrun.real.domain.favourite

import com.jcchrun.real.repositories.repo.FavouriteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteFavouriteUseCaseTest {

    @MockK
    lateinit var mockFavouriteRepository: FavouriteRepository

    private lateinit var useCase: DeleteFavouriteUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = DeleteFavouriteUseCase(
            mockFavouriteRepository
        )
    }

    @Test
    fun `test execute`() {
        val realEstateId = 12
        coEvery { mockFavouriteRepository.deleteFavourite(any()) } answers { }
        runBlocking {
            useCase.execute(realEstateId)
        }
        coVerify { mockFavouriteRepository.deleteFavourite(realEstateId) }
    }
}