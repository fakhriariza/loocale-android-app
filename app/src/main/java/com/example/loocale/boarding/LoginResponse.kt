package com.example.loocale.boarding

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
@Keep
data class LoginResponse(
    @Expose
    @SerializedName("user_name")
    val userName: String? = null,
    @Expose
    @SerializedName("password")
    val password: String? = null,
)
