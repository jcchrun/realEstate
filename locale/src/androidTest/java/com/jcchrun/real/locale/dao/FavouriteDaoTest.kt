package com.jcchrun.real.locale.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.jcchrun.real.locale.db.AppDatabase
import com.jcchrun.real.locale.entities.FavouriteEntity
import kotlinx.coroutines.runBlocking
import org.junit.*

class FavouriteDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var favouriteDao: FavouriteDao

    @Before
    fun setupEmptyTable() {
        tearDown()
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(
            appContext,
            AppDatabase::class.java
        )
            .fallbackToDestructiveMigration()
            .build()
        favouriteDao = database.favouriteDao()
    }

    @After
    fun tearDown() {
        if (this::database.isInitialized) {
            database.close()
        }
    }

    @Test
    fun testInsert() {
        Assert.assertEquals(
            1,
            runBlocking {
                favouriteDao.insert(FavouriteEntity(1, 2))
            }
        )
    }

    @Test
    fun testInsertSameEntity() {
        val entity = FavouriteEntity(1, 2)
        Assert.assertEquals(
            1,
            runBlocking {
                favouriteDao.insert(entity)
            }
        )
        Assert.assertEquals(
            1,
            runBlocking {
                favouriteDao.insert(entity)
            }
        )
        Assert.assertEquals(
            1,
            runBlocking {
                favouriteDao.getCount()
            }
        )
    }

    @Test
    fun testDelete() {
        val entity = FavouriteEntity(1, 2)
        Assert.assertEquals(
            1,
            runBlocking {
                favouriteDao.insert(entity)
            }
        )
        Assert.assertEquals(
            1,
            runBlocking {
                favouriteDao.getCount()
            }
        )
        Assert.assertEquals(
            0,
            runBlocking {
                favouriteDao.delete(entity.realEstateId)
                favouriteDao.getCount()
            }
        )
    }

    @Test
    fun testDeleteWrongId() {
        val entity = FavouriteEntity(1, 2)
        Assert.assertEquals(
            1,
            runBlocking {
                favouriteDao.insert(entity)
            }
        )
        Assert.assertEquals(
            1,
            runBlocking {
                favouriteDao.getCount()
            }
        )
        Assert.assertEquals(
            1,
            runBlocking {
                favouriteDao.delete(333)
                favouriteDao.getCount()
            }
        )
    }

    @Test
    fun testGetFromEmptyTable() {
        Assert.assertNull(
            runBlocking {
                favouriteDao.get(1)
            }
        )
    }

    @Test
    fun testGetFromMissingId() {
        val entity = FavouriteEntity(1, 2)
        Assert.assertEquals(
            1,
            runBlocking {
                favouriteDao.insert(entity)
            }
        )
        Assert.assertNull(
            runBlocking {
                favouriteDao.get(333)
            }
        )
    }

    @Test
    fun testGet() {
        val entity = FavouriteEntity(1, 2)
        Assert.assertEquals(
            1,
            runBlocking {
                favouriteDao.insert(entity)
            }
        )
        Assert.assertEquals(
            entity,
            runBlocking {
                favouriteDao.get(entity.realEstateId)
            }
        )
    }
}