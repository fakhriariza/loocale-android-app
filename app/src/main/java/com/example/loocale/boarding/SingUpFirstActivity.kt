package com.example.loocale.boarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loocale.R
import com.example.loocale.databinding.ActivityBoardingOneBinding
import com.example.loocale.databinding.ActivitySignupfirstBinding

class SingUpFirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupfirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(R.layout.activity_signupfirst)
        binding = ActivitySignupfirstBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tvGoToSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.ivBackArrow.setOnClickListener{
            onBackPressed()
        }
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, ConfirmOTPActivity::class.java)
            startActivity(intent)
        }
    }

}