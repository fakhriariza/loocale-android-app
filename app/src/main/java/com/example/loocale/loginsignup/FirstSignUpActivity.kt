package com.example.loocale.loginsignup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.loocale.R
import com.example.loocale.databinding.ActivitySignupfirstBinding

class FirstSignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupfirstBinding
    private lateinit var viewModel: LoginSignUpVM

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupfirstBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoginSignUpVM::class.java)


        binding.apply {
            btnSignUp.background = getDrawable(R.drawable.secondary_button)
            btnSignUp.isEnabled = false
            etSignUp.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    tvError.visibility = View.GONE
                    btnSignUp.isEnabled = false
                    btnSignUp.background = getDrawable(R.drawable.secondary_button)
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (TextUtils.isEmpty(etSignUp.text.toString())) {
                        tvError.visibility = View.VISIBLE
                        btnSignUp.isEnabled = false
                        btnSignUp.background = getDrawable(R.drawable.secondary_button)

                    } else {
                        tvError.visibility = View.GONE
                        btnSignUp.isEnabled = true
                        btnSignUp.background = getDrawable(R.drawable.primary_button)
                    }
                }
                override fun afterTextChanged(p0: Editable?) {
                }

            })

            tvGoToSignIn.setOnClickListener {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
            }
            ivBackArrow.setOnClickListener {
                onBackPressed()
            }
            btnSignUp.setOnClickListener {
                condition()
            }
        }
    }

    private fun condition() {
        val email = binding.etSignUp.text.toString()
        val emailData = SendEmailResponse(email)
        val bundle = Bundle()
        bundle.putString("email", email)
        viewModel.sendEmail(emailData)
        viewModel.isErrorFirstSignUpState().observe(this) {
            if (it == null) return@observe
            if (it) {
                binding.tvError.apply {
                    visibility = View.VISIBLE
                    text = "Email sudah terdaftar"
                }
            } else {
                binding.tvError.visibility = View.GONE
                val intent = Intent(this, ConfirmOTPActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }
}