package com.jcchrun.real.presentation.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jcchrun.real.commons.app.viewmodels.AppDispatchers
import com.jcchrun.real.domain.favourite.HasFavouriteUseCase
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
class RealEstateListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @MockK
    lateinit var mockFetchRealEstateListUseCase: FetchRealEstateListUseCase

    @MockK
    lateinit var mockHasFavouriteUseCase: HasFavouriteUseCase

    @MockK
    lateinit var mockRealEstateList: RealEstateList

    @MockK
    lateinit var mockRealEstateItem: RealEstateItem

    private lateinit var realEstateListViewModel: RealEstateListViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        realEstateListViewModel = RealEstateListViewModel(
            AppDispatchers(coroutineScope.dispatcher, coroutineScope.dispatcher),
            mockFetchRealEstateListUseCase,
            mockHasFavouriteUseCase
        )
    }

    @Test
    fun `test fetchData error`() {
        val expected = Output.Error(1, "", null)
        coEvery { mockFetchRealEstateListUseCase.execute() } returns expected
        realEstateListViewModel.fetchData()
        val output = realEstateListViewModel.dataLiveData.getValueForTest()
        Assert.assertEquals(expected, output)
        coVerify { mockFetchRealEstateListUseCase.execute() }
    }

    @Test
    fun `test fetchData success`() {
        coEvery { mockFetchRealEstateListUseCase.execute() } returns Output.Success(mockRealEstateList)
        coEvery { mockRealEstateList.items } returns listOf(mockRealEstateItem)
        coEvery { mockRealEstateItem.id } returns 23
        coEvery { mockHasFavouriteUseCase.execute(any()) } returns true
        realEstateListViewModel.fetchData()
        val output = realEstateListViewModel.dataLiveData.getValueForTest()
        Assert.assertTrue(output is Output.Success)
        Assert.assertTrue((output as Output.Success).result[0].favourite)
        Assert.assertEquals(mockRealEstateItem, (output as Output.Success).result[0].realEstateItem)
        coVerify { mockFetchRealEstateListUseCase.execute() }
        coVerify { mockHasFavouriteUseCase.execute(23) }
    }
}