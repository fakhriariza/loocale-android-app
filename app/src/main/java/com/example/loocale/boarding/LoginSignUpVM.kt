package com.example.loocale.boarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loocale.networking.LoocaleApi
import com.example.loocale.networking.LoocaleApiService
import retrofit2.Call
import retrofit2.Response

class LoginSignUpVM: ViewModel() {
    private var sendEmail: SendEmailResponse? = null
    val sendEmailResponse = MutableLiveData<SendEmailResponse>()
    val validateOtpResponse = MutableLiveData<ValidateOtpResponse>()
    val sendFormSignUp = MutableLiveData<SignUpResponse>()
    val sendSignIn = MutableLiveData<LoginResponse>()
    val retrofit = LoocaleApi.getInstance(LoocaleApiService::class.java)
    private val errorState = MutableLiveData<Boolean>()

    fun sendEmail (emailData: SendEmailResponse) {
        val apiCall = retrofit.sendEmail(emailData)
        apiCall?.enqueue(object : retrofit2.Callback<SendEmailResponse?> {
            override fun onResponse(
                call: Call<SendEmailResponse?>,
                response: Response<SendEmailResponse?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        sendEmailResponse.value = it
                        errorState.value = false
                    }
                } else if (response.code() == 400){
                    errorState.value = true
                }
            }
            override fun onFailure(call: Call<SendEmailResponse?>, t: Throwable) {
                errorState.value = false
            }
            })
    }

    fun validateOtp (validateOtpData: ValidateOtpResponse) {
        val apiCall = retrofit.validateOtp(validateOtpData)
        apiCall?.enqueue(object : retrofit2.Callback<ValidateOtpResponse?> {
            override fun onResponse(
                call: Call<ValidateOtpResponse?>,
                response: Response<ValidateOtpResponse?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        validateOtpResponse.value = it
                        errorState.value = false
                    }
                } else if (response.code() == 400){
                    errorState.value = true
                }
            }

            override fun onFailure(call: Call<ValidateOtpResponse?>, t: Throwable) {
                errorState.value = true
            }
        })
    }

    fun resendOtp (resendOtp: SendEmailResponse) {
        val apiCall = retrofit.resendOtp(resendOtp)
        apiCall?.enqueue(object : retrofit2.Callback<SendEmailResponse?> {
            override fun onResponse(
                call: Call<SendEmailResponse?>,
                response: Response<SendEmailResponse?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        sendEmailResponse.value = it
                        errorState.value = false
                    }
                } else if (response.code() == 400){
                    errorState.value = true
                }
            }
            override fun onFailure(call: Call<SendEmailResponse?>, t: Throwable) {
                errorState.value = false
            }
        })
    }

    fun sendSignUpData (sendSignUpData: SignUpResponse) {
        val apiCall = retrofit.signUpForm(sendSignUpData)
        apiCall?.enqueue(object : retrofit2.Callback<SignUpResponse?> {
            override fun onResponse(
                call: Call<SignUpResponse?>,
                response: Response<SignUpResponse?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        sendFormSignUp.value = it
                        errorState.value = false
                    }
                } else if (response.code() == 400){
                    errorState.value = true
                }
            }
            override fun onFailure(call: Call<SignUpResponse?>, t: Throwable) {
                errorState.value = false
            }
        })
    }

    fun sendSignInData (signInResponse: LoginResponse) {
        val apiCall = retrofit.loginForm(signInResponse)
        apiCall?.enqueue(object : retrofit2.Callback<LoginResponse?> {
            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        sendSignIn.value = it
                        errorState.value = false
                    }
                } else if (response.code() == 400){
                    errorState.value = true
                }
            }
            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                errorState.value = false
            }
        })
    }

    fun isErrorState(): LiveData<Boolean> {
        return errorState
    }
}