package com.jcchrun.real.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jcchrun.real.commons.app.viewmodels.AppDispatchers
import com.jcchrun.real.domain.favourite.DeleteFavouriteUseCase
import com.jcchrun.real.domain.favourite.HasFavouriteUseCase
import com.jcchrun.real.domain.favourite.InsertFavouriteUseCase
import com.jcchrun.real.domain.realEstate.FetchRealEstateListUseCase
import com.jcchrun.real.models.Output
import com.jcchrun.real.models.RealEstateItem
import com.jcchrun.real.models.RealEstateList
import com.jcchrun.real.presentation.detail.RealEstateDetailViewModel
import com.jcchrun.real.testutils.MainCoroutineScopeRule
import com.jcchrun.real.testutils.getValueForTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RealEstateDetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @MockK
    lateinit var mockHasFavouriteUseCase: HasFavouriteUseCase

    @MockK
    lateinit var mockInsertFavouriteUseCase: InsertFavouriteUseCase

    @MockK
    lateinit var mockDeleteFavouriteUseCase: DeleteFavouriteUseCase

    @MockK
    lateinit var mockRealEstateList: RealEstateList

    @MockK
    lateinit var mockRealEstateItem: RealEstateItem

    private lateinit var realEstateDetailViewModel: RealEstateDetailViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        realEstateDetailViewModel = RealEstateDetailViewModel(
            AppDispatchers(coroutineScope.dispatcher, coroutineScope.dispatcher),
            mockHasFavouriteUseCase,
            mockInsertFavouriteUseCase,
            mockDeleteFavouriteUseCase
        )
    }

    @Test
    fun `test getFavourite`() {
        coEvery { mockHasFavouriteUseCase.execute(any()) } returns true
        coEvery { mockRealEstateItem.id } returns 23
        realEstateDetailViewModel.getFavourite(mockRealEstateItem)
        val hasFavourite = realEstateDetailViewModel.favouriteLiveData.getValueForTest() ?: false
        Assert.assertTrue(hasFavourite)
        coVerify { mockHasFavouriteUseCase.execute(23) }
    }

    @Test
    fun `test saveFavourite`() {
        coEvery { mockInsertFavouriteUseCase.execute(any()) } answers { }
        coEvery { mockRealEstateItem.id } returns 23
        realEstateDetailViewModel.saveFavourite(mockRealEstateItem)
        val hasFavourite = realEstateDetailViewModel.favouriteLiveData.getValueForTest() ?: false
        Assert.assertTrue(hasFavourite)
        coVerify { mockInsertFavouriteUseCase.execute(23) }
    }

    @Test
    fun `test deleteFavourite`() {
        coEvery { mockDeleteFavouriteUseCase.execute(any()) } answers { }
        coEvery { mockRealEstateItem.id } returns 23
        realEstateDetailViewModel.deleteFavourite(mockRealEstateItem)
        val hasFavourite = realEstateDetailViewModel.favouriteLiveData.getValueForTest() ?: false
        Assert.assertFalse(hasFavourite)
        coVerify { mockDeleteFavouriteUseCase.execute(23) }
    }
}