package com.example.loocale.networking

import com.example.loocale.boarding.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoocaleApiService {

    @POST("user/email")
    fun sendEmail(
        @Body sendEmailRequest: SendEmailResponse?):
            Call<SendEmailResponse?>?

    @POST("user/signup/form")
    fun signUpForm(
        @Body signUpResponse: SignUpResponse?):
            Call<SignUpResponse?>?

    @POST("user/validate/username")
    fun validateUsername(
        @Body validateUsername: ValidateUsernameResponse?):
            Call<ValidateUsernameResponse?>?

    @POST("user/validate/otp")
    fun validateOtp(
        @Body validateOtp: ValidateOtpResponse?):
            Call<ValidateOtpResponse?>?

    @POST("user/resend/otp")
    fun resendOtp(
        @Body resendOtp: SendEmailResponse?):
            Call<SendEmailResponse?>?

    @POST("login")
    fun loginForm(
        @Body login: LoginResponse?):
            Call<LoginResponse?>?
}