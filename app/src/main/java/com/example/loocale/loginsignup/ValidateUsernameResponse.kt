package com.example.loocale.loginsignup

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
@Keep
data class ValidateUsernameResponse(
    @Expose
    @SerializedName("user_name")
    val dataEmail: String? = null
)
