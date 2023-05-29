package com.example.loocale.profilecreate

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
@Keep
data class ConnectResponse(
    @Expose
    @SerializedName("data")
    val data: List<ConnectId> = arrayListOf()
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Keep
    data class ConnectId(
        @Expose
        @SerializedName("id")
        val id: Int? = null,
        @Expose
        @SerializedName("background")
        val background: String? = "",
        @Expose
        @SerializedName("title")
        val title: String? = "",
    )

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }
}
