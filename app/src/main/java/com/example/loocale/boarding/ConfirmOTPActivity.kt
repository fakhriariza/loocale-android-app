package com.example.loocale.boarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.chaos.view.PinView
import com.example.loocale.MainActivity
import com.example.loocale.R
import com.example.loocale.databinding.ActivityConfirmotpBinding
import com.example.loocale.databinding.ActivityLoginBinding

class ConfirmOTPActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmotpBinding
    private lateinit var viewModel: LoginSignUpVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmotp)
        binding = ActivityConfirmotpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this).get(LoginSignUpVM::class.java)
        val email = intent.extras?.getString("email")

        binding.ivBackArrow.setOnClickListener{
            onBackPressed()
        }
        binding.btnVerification.setOnClickListener {
            sendOtp(email)
        }

        binding.tvResend.setOnClickListener{
            resendOtp(email)
        }

        val pinView = binding.firstPinView
        pinView.setItemBackgroundResources(R.drawable.edittext_backgroundlogin)
    }

    fun sendOtp(email: String?) {
        val otp = binding.firstPinView.text.toString().toInt()
        val otpData = ValidateOtpResponse(email, otp)
        viewModel.validateOtp(otpData)
        Toast.makeText(this, "ini emailnya $email & ini otpnya $otp", Toast.LENGTH_SHORT).show()

        val bundles = Bundle()
        bundles.putString("email", email)
        val intent = Intent(this, SignUpActivity::class.java)
        intent.putExtras(bundles)
        startActivity(intent)

//        viewModel.isErrorState().observe(this, { aBoolean: Boolean? ->
//            if (aBoolean == null) return@observe
//            if (aBoolean) {
//                return@observe
//            } else {
//                val intent = Intent(this, SignUpActivity::class.java)
//                startActivity(intent)
//            }
//        })
    }

    fun resendOtp(email: String?) {
        val resendOtp = SendEmailResponse(email)
        viewModel.resendOtp(resendOtp)
        Toast.makeText(this, "udah ke kirim yak emailnya ke $email", Toast.LENGTH_SHORT).show()

    }

}