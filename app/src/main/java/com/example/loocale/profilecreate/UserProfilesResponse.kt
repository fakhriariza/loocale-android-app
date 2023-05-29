package com.example.loocale.profilecreate

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
@Keep
data class UserProfilesResponse(
    @Expose
    @SerializedName("province")
    val province: String? = null,
    @Expose
    @SerializedName("city")
    val city: String? = null,
    @Expose
    @SerializedName("profileImage")
    val profileImage: String? = null,
    @Expose
    @SerializedName("connectId")
    val connectId: String? = null,
)
