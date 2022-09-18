package Model

import android.os.Parcel
import android.os.Parcelable

data class Hewan(
    var namanya:String?,
    var jenisnya:String?,
    var usianya:String?
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    var imageUri:String = ""
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(namanya)
        parcel.writeString(jenisnya)
        parcel.writeValue(usianya)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hewan> {
        override fun createFromParcel(parcel: Parcel): Hewan {
            return Hewan(parcel)
        }

        override fun newArray(size: Int): Array<Hewan?> {
            return arrayOfNulls(size)
        }
    }
}