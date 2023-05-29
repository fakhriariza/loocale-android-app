package com.example.loocale.loginsignup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loocale.profilecreate.CitiesResponse
import com.example.loocale.profilecreate.ConnectResponse
import com.example.loocale.profilecreate.ProvincesResponse
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
    val provinceData = MutableLiveData<ProvincesResponse>()
    val connectData = MutableLiveData<ConnectResponse>()
    val citiesData = MutableLiveData<CitiesResponse>()
    val errorOtpState = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<Boolean>()
    val errorLoginState = MutableLiveData<Boolean>()
    val errorFirstSignUpState = MutableLiveData<Boolean>()
    val retrofit = LoocaleApi.getInstance(LoocaleApiService::class.java)


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
                        errorFirstSignUpState.value = false
                    }
                } else if (response.code() == 400){
                    errorFirstSignUpState.value = true
                } else {
                    errorFirstSignUpState.value = true
                }
            }
            override fun onFailure(call: Call<SendEmailResponse?>, t: Throwable) {
                errorFirstSignUpState.value = false
            }
        })
    }

    fun isErrorFirstSignUpState(): LiveData<Boolean> {
        return errorFirstSignUpState
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
                        errorOtpState.value = false
                    }
                } else if (response.code() == 400){
                    errorOtpState.value = true
                } else {
                    errorOtpState.value = true
                }
            }

            override fun onFailure(call: Call<ValidateOtpResponse?>, t: Throwable) {
                errorOtpState.value = true
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
                        errorOtpState.value = false
                    }
                } else if (response.code() == 400){
                    errorOtpState.value = true
                } else {
                    errorOtpState.value = true
                }
            }
            override fun onFailure(call: Call<SendEmailResponse?>, t: Throwable) {
                errorOtpState.value = false
            }
        })
    }

    fun isErrorOtpState(): LiveData<Boolean> {
        return errorOtpState
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
                        errorLoginState.value = false
                    }
                } else if (response.code() == 400){
                    errorLoginState.value = true
                } else {
                    errorLoginState.value = true
                }
            }
            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                errorLoginState.value = true
            }
        })
    }

    fun isErrorLoginState(): LiveData<Boolean> {
        return errorLoginState
    }

    fun getProvincesData () {
        val apiCall = retrofit.getProvincesData()
        apiCall?.enqueue(object : retrofit2.Callback<ProvincesResponse?> {
            override fun onResponse(
                call: Call<ProvincesResponse?>,
                response: Response<ProvincesResponse?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        provinceData.value = it
                    }
                } else if (response.code() == 400){
                }
            }
            override fun onFailure(call: Call<ProvincesResponse?>, t: Throwable) {
            }
        })
    }

    fun getCitiesData (provinces: String) {
        val apiCall = retrofit.sendCities(provinces)
        apiCall?.enqueue(object : retrofit2.Callback<CitiesResponse?> {
            override fun onResponse(
                call: Call<CitiesResponse?>,
                response: Response<CitiesResponse?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        citiesData.value = it
                    }
                } else if (response.code() == 400){
                    println("400 bro ${response.errorBody()?.string()}")
                }
            }
            override fun onFailure(call: Call<CitiesResponse?>, t: Throwable) {
                println("gagal bro")
            }
        })
    }

    fun getConnectData() {
        val apiCall = retrofit.getConnectData()
        apiCall?.enqueue(object : retrofit2.Callback<ConnectResponse?> {
            override fun onResponse(
                call: Call<ConnectResponse?>,
                response: Response<ConnectResponse?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        connectData.value = it
                    }
                }
            }

            override fun onFailure(call: Call<ConnectResponse?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun isErrorState(): LiveData<Boolean> {
        return errorState
    }
}