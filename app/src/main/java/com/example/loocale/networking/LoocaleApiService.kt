package com.example.loocale.networking

import com.example.loocale.profilecreate.UserProfilesResponse
import com.example.loocale.loginsignup.*
import com.example.loocale.profilecreate.CitiesResponse
import com.example.loocale.profilecreate.ConnectResponse
import com.example.loocale.profilecreate.ProvincesResponse
import retrofit2.Call
import retrofit2.http.*

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
    fun  resendOtp(
        @Body resendOtp: SendEmailResponse?):
            Call<SendEmailResponse?>?

    @POST("login")
    fun loginForm(
        @Body login: LoginResponse?):
            Call<LoginResponse?>?

    @GET("provinces")
    fun getProvincesData():
            Call<ProvincesResponse?>?

    @GET("cities")
    fun sendCities(
        @Query("province") province: String
    ): Call<CitiesResponse?>?

    @Multipart
    @POST("userprofiles")
    fun userProfiles(
        @Body login: UserProfilesResponse?):
            Call<UserProfilesResponse?>?

    @GET("productDetail")
    fun getConnectData(
    ): Call<ConnectResponse?>?
}