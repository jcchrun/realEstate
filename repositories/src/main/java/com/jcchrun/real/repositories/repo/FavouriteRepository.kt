package com.jcchrun.real.repositories.repo

import com.jcchrun.real.locale.dao.FavouriteDao
import com.jcchrun.real.locale.entities.FavouriteEntity

interface FavouriteRepository {


    suspend fun insertFavourite(realEstateId: Int)
    suspend fun getFavourite(realEstateId: Int): Boolean
    suspend fun deleteFavourite(realEstateId: Int)
}

class FavouriteRepositoryImpl constructor(
    private val favouriteDao: FavouriteDao
): FavouriteRepository {

    override suspend fun insertFavourite(realEstateId: Int) {
        favouriteDao.insert(FavouriteEntity(0, realEstateId))
    }

    override suspend fun getFavourite(realEstateId: Int): Boolean {
        return favouriteDao.get(realEstateId) != null
    }

    override suspend fun deleteFavourite(realEstateId: Int) {
        favouriteDao.delete(realEstateId)
    }
}