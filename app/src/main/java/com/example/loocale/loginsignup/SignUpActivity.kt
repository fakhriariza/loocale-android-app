package com.example.loocale.loginsignup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.loocale.MainActivity
import com.example.loocale.R
import com.example.loocale.databinding.ActivitySignupBinding
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: LoginSignUpVM

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(LoginSignUpVM::class.java)

        val email = intent.extras?.getString("email")


        binding.ivBackArrow.setOnClickListener {
            onBackPressed()
        }

        binding.btnSignUp.apply {
            isEnabled = false
            background = getDrawable(R.drawable.secondary_button)
        }
        sendSignUpData(email)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun sendSignUpData(email: String?) {
        val fullName = binding.etName.text.toString()
        val userName = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()

        if (setValidPassword(password) && setConfirmPassword(confirmPassword, password)
            && setOnlyAlphabet(fullName) && setUsernameValid(userName)) {
            binding.btnSignUp.apply {
                isEnabled = true
                background = getDrawable(R.drawable.primary_button)

                binding.btnSignUp.setOnClickListener {
                    val sendSignUpData = SignUpResponse(email, fullName, userName, password)
                    viewModel.sendSignUpData(sendSignUpData)
                    val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        } else {
            binding.btnSignUp.apply {
                isEnabled = false
                background = getDrawable(R.drawable.secondary_button)
            }
        }
    }

    private fun setUsernameValid(username: String) : Boolean {
        var isValidUsername = false
        binding.apply {
            etUsername.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (etUsername.text.toString().isNotEmpty()) {
                        icErrorUsername.visibility = View.GONE
                        icSuccesUsername.visibility = View.VISIBLE
                        tvUsernameNotif.visibility = View.GONE
                        isValidUsername = true
                    } else {
                        icSuccesUsername.visibility = View.VISIBLE
                        tvUsernameNotif.visibility = View.VISIBLE
                        icSuccesUsername.visibility = View.GONE
                        tvUsernameNotif.text = "Username tidak tersedia"
                        isValidUsername = true
                    }
                }
                override fun afterTextChanged(p0: Editable?) {
                }
            })
        }
        return isValidUsername
    }

    private fun setOnlyAlphabet(fullname: String) : Boolean {
        var isAlphabetOnly = false
        binding.apply {
            etName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (isOnlyAlphabet(fullname)) {
                        icErrorName.visibility = View.GONE
                        icSuccesName.visibility = View.VISIBLE
                        tvNameNotif.visibility = View.GONE
                        isAlphabetOnly = true
                    } else {
                        icErrorName.visibility = View.VISIBLE
                        tvNameNotif.visibility = View.VISIBLE
                        icSuccesName.visibility = View.GONE
                        tvNameNotif.text = "Nama hanya dapat terdiri dari huruf alfabet"
                        isAlphabetOnly = false
                    }
                }
                override fun afterTextChanged(p0: Editable?) {
                }

            })
        }
        return isAlphabetOnly
    }

    private fun setConfirmPassword(confirmPass: String?, password: String?) : Boolean {
        var isConfirmed = false
        binding.apply {
            etConfirmPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (!password.equals(confirmPass)) {
                        binding.icErrorConfirmPassword.visibility = View.VISIBLE
                        binding.tvConfirmPasswordNotif.visibility = View.VISIBLE
                        binding.icSuccesConfirmPassword.visibility = View.GONE
                        binding.tvConfirmPasswordNotif.text = "Password Tidak Sesuai"
                        isConfirmed = false
                    } else {
                        binding.icErrorConfirmPassword.visibility = View.GONE
                        binding.icSuccesConfirmPassword.visibility = View.VISIBLE
                        binding.tvConfirmPasswordNotif.visibility = View.GONE
                        isConfirmed = true
                    }
                }
                override fun afterTextChanged(p0: Editable?) {
                }
            })
        }
        return isConfirmed
    }

    private fun setValidPassword(password: String?) : Boolean {
        var isValid = false
        binding.apply {
            etPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (isValidPassword(password)) {
                        icErrorPassword.visibility = View.GONE
                        icSuccesPassword.visibility = View.VISIBLE
                        tvPasswordNotif.visibility = View.GONE
                        isValid = true
                    } else {
                        icErrorPassword.visibility = View.VISIBLE
                        tvPasswordNotif.visibility = View.VISIBLE
                        icSuccesPassword.visibility = View.GONE
                        tvPasswordNotif.text =
                            "Password harus menggunakan minimal 8 karakter dengan huruf kecil, kapital, dan angka"
                        isValid = false
                    }
                }
                override fun afterTextChanged(p0: Editable?) {
                }
            })
        }
        return isValid
    }

//    private fun isValidPassword(password: String?): Boolean {
//        val pattern: Pattern
//        val matcher: Matcher
//        val PASSWORD_PATTERN =
//            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[|°¬<>!#$%&/()=?'¡¿*+_@^])(?=\\S+$).{8,}$"
//        pattern = Pattern.compile(PASSWORD_PATTERN)
//        matcher = pattern.matcher(password)
//        return matcher.matches()
//    }

    private fun isOnlyAlphabet(fullname: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val ALPHABET =
            "abcdefghijklmnopqrstuvwxyz"
        pattern = Pattern.compile(ALPHABET)
        matcher = pattern.matcher(fullname)
        return matcher.matches()
    }

    private fun isValidPassword(password: String?) : Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(password) != null
        } ?: return false
    }
}
