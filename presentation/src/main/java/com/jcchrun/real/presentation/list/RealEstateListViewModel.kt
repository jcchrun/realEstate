package com.jcchrun.real.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcchrun.real.commons.app.viewmodels.AbstractViewModel
import com.jcchrun.real.commons.app.viewmodels.AppDispatchers
import com.jcchrun.real.domain.favourite.HasFavouriteUseCase
import com.jcchrun.real.domain.realEstate.FetchRealEstateListUseCase
import com.jcchrun.real.models.Output
import com.jcchrun.real.models.RealEstateItem
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RealEstateListViewModel(
    appDispatchers: AppDispatchers,
    private val fetchRealEstateListUseCase: FetchRealEstateListUseCase,
    private val hasFavouriteUseCase: HasFavouriteUseCase
): AbstractViewModel(appDispatchers) {

    private val _dataLiveData = MutableLiveData<Output<List<Result>>>()
    val dataLiveData: LiveData<Output<List<Result>>> = _dataLiveData

    fun fetchData() {
        mainScope.launch {
            val output = ioScope.async { fetchRealEstateListUseCase.execute() }.await()
            when (output) {
                is Output.Error -> _dataLiveData.value = output
                is Output.Success -> {
                    val result = output.result.items.map {
                        val favourite = ioScope.async { hasFavouriteUseCase.execute(it.id) }.await()
                        Result(it, favourite)
                    }
                    _dataLiveData.value = Output.Success(result)
                }
            }
        }
    }

    data class Result(
        val realEstateItem: RealEstateItem,
        val favourite: Boolean
    )

}