package com.jcchrun.real.locale.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = FavouriteTable.TABLE_NAME)
data class FavouriteEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = FavouriteColumnNames.ID)
    val id: Int,
    @ColumnInfo(name = FavouriteColumnNames.REAL_ESTATE_ID)
    val realEstateId: Int
)

object FavouriteTable {
    const val TABLE_NAME = "favourites_table"
}

object FavouriteColumnNames {
    const val ID = "id"
    const val REAL_ESTATE_ID = "real_estate_id"
}