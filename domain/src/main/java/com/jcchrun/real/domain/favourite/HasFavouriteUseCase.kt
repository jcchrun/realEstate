package com.jcchrun.real.domain.favourite

import com.jcchrun.real.repositories.repo.FavouriteRepository

class HasFavouriteUseCase(
    private val favouriteRepository: FavouriteRepository
) {

    suspend fun execute(realEstateId: Int): Boolean {
        return favouriteRepository.getFavourite(realEstateId)
    }
}