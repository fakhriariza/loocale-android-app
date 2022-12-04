package com.example.loocale.boarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.example.loocale.MainActivity
import com.example.loocale.R
import com.example.loocale.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginSignUpVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(LoginSignUpVM::class.java)
        setContentView(view)

        binding.ivBackArrow.setOnClickListener{
            onBackPressed()
        }
        binding.btnSignIn.setOnClickListener{
            signIn()
        }
    }

    fun signIn() {
        val userName = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val signInData = LoginResponse(userName, password)
        viewModel.sendSignInData(signInData)

        Toast.makeText(this, "ini username $userName & ini passwordnya $password", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}