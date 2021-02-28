package com.jcchrun.real.locale.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jcchrun.real.locale.entities.FavouriteColumnNames
import com.jcchrun.real.locale.entities.FavouriteEntity
import com.jcchrun.real.locale.entities.FavouriteTable

@Dao
abstract class FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(favouriteEntity: FavouriteEntity): Long

    @Query("DELETE FROM ${FavouriteTable.TABLE_NAME} WHERE ${FavouriteColumnNames.REAL_ESTATE_ID} =:realEstateId")
    abstract suspend fun delete(realEstateId: Int)

    @Query(
        "SELECT * from ${FavouriteTable.TABLE_NAME}  WHERE ${FavouriteColumnNames.REAL_ESTATE_ID} =:realEstateId"
    )
    abstract suspend fun get(realEstateId: Int): FavouriteEntity?

    @Query(
        "SELECT COUNT(${FavouriteColumnNames.ID}) FROM ${FavouriteTable.TABLE_NAME}"
    )
    abstract suspend fun getCount(): Int
}