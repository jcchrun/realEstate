package com.jcchrun.real.domain.favourite

import com.jcchrun.real.repositories.repo.FavouriteRepository

class InsertFavouriteUseCase(
    private val favouriteRepository: FavouriteRepository
) {

    suspend fun execute(realEstateId: Int) {
        return favouriteRepository.insertFavourite(realEstateId)
    }
}