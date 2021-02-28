package com.jcchrun.real.models

import android.os.Parcel
import android.os.Parcelable

data class RealEstateItem(
    val id: Int,
    val bedrooms: Int,
    val rooms: Int,
    val city: String,
    val area: Double,
    val imageUrl: String,
    val price: Double,
    val professional: String,
    val propertyType: String
): Parcelable {

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<RealEstateItem> {
            override fun createFromParcel(parcel: Parcel) = RealEstateItem(parcel)
            override fun newArray(size: Int) = arrayOfNulls<RealEstateItem>(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        id = parcel.readInt(),
        bedrooms = parcel.readInt(),
        rooms = parcel.readInt(),
        city = parcel.readString() ?: "",
        area = parcel.readDouble(),
        imageUrl = parcel.readString() ?: "",
        price = parcel.readDouble(),
        professional = parcel.readString() ?: "",
        propertyType = parcel.readString() ?: "",
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(bedrooms)
        parcel.writeInt(rooms)
        parcel.writeString(city)
        parcel.writeDouble(area)
        parcel.writeString(imageUrl)
        parcel.writeDouble(price)
        parcel.writeString(professional)
        parcel.writeString(propertyType)
    }

    override fun describeContents() = 0
}