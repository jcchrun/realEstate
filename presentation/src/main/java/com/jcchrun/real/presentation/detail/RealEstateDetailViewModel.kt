package com.jcchrun.real.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jcchrun.real.commons.app.viewmodels.AbstractViewModel
import com.jcchrun.real.commons.app.viewmodels.AppDispatchers
import com.jcchrun.real.domain.favourite.DeleteFavouriteUseCase
import com.jcchrun.real.domain.favourite.HasFavouriteUseCase
import com.jcchrun.real.domain.favourite.InsertFavouriteUseCase
import com.jcchrun.real.models.Output
import com.jcchrun.real.models.RealEstateItem
import com.jcchrun.real.presentation.list.RealEstateListViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RealEstateDetailViewModel(
    appDispatchers: AppDispatchers,
    private val hasFavouriteUseCase: HasFavouriteUseCase,
    private val insertFavouriteUseCase: InsertFavouriteUseCase,
    private val deleteFavouriteUseCase: DeleteFavouriteUseCase
): AbstractViewModel(appDispatchers) {

    private val _favouriteLiveData = MutableLiveData<Boolean>()
    val favouriteLiveData: LiveData<Boolean> = _favouriteLiveData

    fun getFavourite(realEstateItem: RealEstateItem) {
        mainScope.launch {
            val hasFavourite = ioScope.async {
                hasFavouriteUseCase.execute(realEstateItem.id)
            }.await()
            _favouriteLiveData.value = hasFavourite
        }
    }

    fun saveFavourite(realEstateItem: RealEstateItem) {
        mainScope.launch {
            ioScope.async { insertFavouriteUseCase.execute(realEstateItem.id) }.await()
            _favouriteLiveData.value = true
        }
    }

    fun deleteFavourite(realEstateItem: RealEstateItem) {
        mainScope.launch {
            ioScope.async { deleteFavouriteUseCase.execute(realEstateItem.id) }.await()
            _favouriteLiveData.value = false
        }
    }
}