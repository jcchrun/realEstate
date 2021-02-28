package com.jcchrun.real.domain.favourite

import com.jcchrun.real.repositories.repo.FavouriteRepository

class DeleteFavouriteUseCase(
    private val favouriteRepository: FavouriteRepository
) {

    suspend fun execute(realEstateId: Int) {
        return favouriteRepository.deleteFavourite(realEstateId)
    }
}