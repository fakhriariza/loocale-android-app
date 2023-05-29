package com.example.loocale.profilecreate

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
@Keep
data class CitiesResponse(
    @Expose
    @SerializedName("data")
    val data: List<Cities> = arrayListOf()
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Keep
    data class Cities(
        @Expose
        @SerializedName("name")
        val name: String = "",
        @Expose
        @SerializedName("province")
        val province: String? = "",
        @Expose
        @SerializedName("region")
        val region: String? = "",
    )

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }
}
