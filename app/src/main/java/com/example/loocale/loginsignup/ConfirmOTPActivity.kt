package com.example.loocale.loginsignup

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.loocale.R
import com.example.loocale.databinding.ActivityConfirmotpBinding

class ConfirmOTPActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmotpBinding
    private lateinit var viewModel: LoginSignUpVM

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityConfirmotpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(LoginSignUpVM::class.java)
        val email = intent.extras?.getString("email")

        binding.apply {
            ivBackArrow.setOnClickListener {
                onBackPressed()
            }
            tvResend.setOnClickListener {
                resendOtp(email)
            }

            btnVerification.apply {
                background = getDrawable(R.drawable.secondary_button)
                isEnabled = false
            }
            firstPinView.setItemBackgroundResources(R.drawable.edittext_backgroundlogin)
            firstPinView.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0?.length == 6) {
                        btnVerification.apply {
                            background = getDrawable(R.drawable.primary_button)
                            isEnabled = true
                        }
                    } else {
                        btnVerification.apply {
                            background = getDrawable(R.drawable.secondary_button)
                            isEnabled = false
                        }
                    }
                }
                override fun afterTextChanged(p0: Editable?) {
                }
            })
            btnVerification.setOnClickListener {
                sendOtp(email)
            }
        }
    }

    private fun sendOtp(email: String?) {
        val otp = binding.firstPinView.text.toString().toInt()
        val otpData = ValidateOtpResponse(email, otp)
        viewModel.validateOtp(otpData)
        viewModel.isErrorOtpState().observe(this) {
            if (it == null) return@observe
            if (it) {
                binding.tvError.visibility = View.VISIBLE
            } else {
                binding.tvError.visibility = View.GONE
                val bundles = Bundle()
                bundles.putString("email", email)
                val intent = Intent(this, SignUpActivity::class.java)
                intent.putExtras(bundles)
                startActivity(intent)
            }
        }
    }

    private fun resendOtp(email: String?) {
        val resendOtp = SendEmailResponse(email)
        viewModel.resendOtp(resendOtp)
        Toast.makeText(this, "OTP Berhasil Dikirim Ulang", Toast.LENGTH_SHORT).show()

    }

}