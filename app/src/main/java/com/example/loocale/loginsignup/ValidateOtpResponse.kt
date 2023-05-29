package com.example.loocale.loginsignup

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
@Keep
data class ValidateOtpResponse(
    @Expose
    @SerializedName("email")
    val dataEmail: String? = null,
    @Expose
    @SerializedName("OTP")
    val otpData: Int? = null,
)
