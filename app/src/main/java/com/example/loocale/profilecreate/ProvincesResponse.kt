package com.example.loocale.profilecreate

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
@Keep
data class ProvincesResponse(
    @Expose
    @SerializedName("data")
    val data: List<String> = arrayListOf()
)
