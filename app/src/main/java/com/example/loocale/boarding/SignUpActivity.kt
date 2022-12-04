package com.example.loocale.boarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.loocale.MainActivity
import com.example.loocale.R
import com.example.loocale.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: LoginSignUpVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this).get(LoginSignUpVM::class.java)
        val email = intent.extras?.getString("email")

        binding.ivBackArrow.setOnClickListener{
            onBackPressed()
        }
        binding.btnSignUp.setOnClickListener{
            sendSignUpData(email)
        }
    }

    fun sendSignUpData (email: String?) {
        val fullName = binding.etName.text.toString()
        val userName = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val sendSignUpData = SignUpResponse(email, fullName, userName, password)
        viewModel.sendSignUpData(sendSignUpData)
        Toast.makeText(this, "ini emailnya $email & ini namanya $fullName, $userName, $password", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}