package com.example.loocale.loginsignup

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.loocale.MainActivity
import com.example.loocale.R
import com.example.loocale.databinding.ActivityLoginBinding
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginSignUpVM

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(LoginSignUpVM::class.java)

        binding.apply {
            btnSignIn.background = getDrawable(R.drawable.secondary_button)
            btnSignIn.isEnabled = false
            etUsername.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    btnSignIn.background = getDrawable(R.drawable.secondary_button)
                    btnSignIn.isEnabled = false
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    binding.tvError.visibility = View.GONE
                    if (TextUtils.isEmpty(etUsername.text.toString())) {
                        btnSignIn.background = getDrawable(R.drawable.secondary_button)
                        btnSignIn.isEnabled = false
                    } else {
                        if (TextUtils.isEmpty(etPassword.text.toString())) {
                            btnSignIn.background = getDrawable(R.drawable.secondary_button)
                            btnSignIn.isEnabled = false
                        } else {
                            btnSignIn.background = getDrawable(R.drawable.primary_button)
                            btnSignIn.isEnabled = true
                        }
                    }
                }
                override fun afterTextChanged(p0: Editable?) {
                }

            })

            etPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    btnSignIn.background = getDrawable(R.drawable.secondary_button)
                    btnSignIn.isEnabled = false
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    binding.tvError.visibility = View.GONE
                    if (TextUtils.isEmpty(etPassword.text.toString())) {
                        btnSignIn.background = getDrawable(R.drawable.secondary_button)
                        btnSignIn.isEnabled = false
                    } else {
                        if (TextUtils.isEmpty(etUsername.text.toString())) {
                            btnSignIn.background = getDrawable(R.drawable.secondary_button)
                            btnSignIn.isEnabled = false
                        } else {
                            btnSignIn.background = getDrawable(R.drawable.primary_button)
                            btnSignIn.isEnabled = true
                        }
                    }
                }
                override fun afterTextChanged(p0: Editable?) {
                }
            })
        }
        binding.ivBackArrow.setOnClickListener{
            onBackPressed()
        }
        binding.btnSignIn.setOnClickListener{
            signIn()
        }
    }
    private fun signIn() {
        binding.apply {
            val userName = etUsername.text.toString()
            val password = etPassword.text.toString()
            val signInData = LoginResponse(userName, password)
            viewModel.sendSignInData(signInData)

            viewModel.isErrorLoginState().observe(this@LoginActivity) {
                if (it == null) return@observe
                if (it) {
                    binding.tvError.visibility = View.VISIBLE
                } else {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.putExtra("Username", etUsername.text.toString())
                    startActivity(intent)
                }
            }
        }
    }

}