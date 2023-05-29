package com.example.loocale.loginsignup

import android.annotation.SuppressLint
import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
@Keep
data class SendEmailResponse(
    @Expose
    @SerializedName("email")
    val dataEmail: String? = null
)
