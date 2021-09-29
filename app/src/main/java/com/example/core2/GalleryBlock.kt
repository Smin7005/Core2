package com.example.core2
import android.os.Parcel
import android.os.Parcelable
data class GalleryBlock(
    var name: String,
    var location: String,
    var rating: Double,
    var date: String
    ):Parcelable {
    constructor(parcel: Parcel): this(
        name = parcel.readString()!!,
        location = parcel.readString()!!,
        rating = parcel.readDouble(),
        date = parcel.readString()!!
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(location)
        parcel.writeDouble(rating)
        parcel.writeString(date)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<GalleryBlock>{
        override fun createFromParcel(parcel: Parcel): GalleryBlock {
            return GalleryBlock(parcel)
        }

        override fun newArray(size: Int): Array<GalleryBlock?> {
            return arrayOfNulls(size)
        }
    }
    }