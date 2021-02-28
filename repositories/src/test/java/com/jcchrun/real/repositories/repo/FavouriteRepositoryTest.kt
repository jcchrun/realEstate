package com.jcchrun.real.repositories.repo

import com.jcchrun.real.locale.dao.FavouriteDao
import com.jcchrun.real.locale.entities.FavouriteEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FavouriteRepositoryTest {

    @MockK
    lateinit var mockFavouriteDao: FavouriteDao

    @MockK
    lateinit var mockFavouriteEntity: FavouriteEntity

    private lateinit var repository: FavouriteRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = FavouriteRepositoryImpl(
            mockFavouriteDao
        )
    }

    @Test
    fun `test insertFavourite`() {
        val realEstateId = 12
        val slot = slot<FavouriteEntity>()
        coEvery { mockFavouriteDao.insert(capture(slot)) } returns 1L
        runBlocking {
            repository.insertFavourite(realEstateId)
        }
        coVerify { mockFavouriteDao.insert(any()) }
        Assert.assertEquals(12, slot.captured.realEstateId)
    }

    @Test
    fun `test getFavourite founded`() {
        val realEstateId = 12
        coEvery { mockFavouriteDao.get(realEstateId) } returns mockFavouriteEntity
        val hasFavourite = runBlocking {
            repository.getFavourite(realEstateId)
        }
        coVerify { mockFavouriteDao.get(realEstateId) }
        Assert.assertEquals(true, hasFavourite)
    }

    @Test
    fun `test getFavourite not founded`() {
        val realEstateId = 12
        coEvery { mockFavouriteDao.get(realEstateId) } returns null
        val hasFavourite = runBlocking {
            repository.getFavourite(realEstateId)
        }
        coVerify { mockFavouriteDao.get(realEstateId) }
        Assert.assertEquals(false, hasFavourite)
    }



    @Test
    fun `test deleteFavourite`() {
        val realEstateId = 12
        coEvery { mockFavouriteDao.delete(realEstateId) } answers { }
        runBlocking {
            repository.deleteFavourite(realEstateId)
        }
        coVerify { mockFavouriteDao.delete(realEstateId) }
    }
}