package com.example.loocale.boarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.chaos.view.PinView
import com.example.loocale.MainActivity
import com.example.loocale.R
import com.example.loocale.databinding.ActivityConfirmotpBinding
import com.example.loocale.databinding.ActivityLoginBinding

class ConfirmOTPActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmotpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmotp)
        binding = ActivityConfirmotpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.ivBackArrow.setOnClickListener{
            onBackPressed()
        }
        binding.btnVerification.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val pinView = binding.firstPinView
        pinView.setItemBackgroundResources(R.drawable.edittext_backgroundlogin)
    }

}