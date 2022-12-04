package com.example.loocale.boarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.loocale.R
import com.example.loocale.databinding.ActivitySignupfirstBinding

class SingUpFirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupfirstBinding
    private var email: String? = null
    private lateinit var viewModel: LoginSignUpVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(R.layout.activity_signupfirst)
        binding = ActivitySignupfirstBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (binding.etSignUp.text.toString().trim().length == 0) {
            binding.tvError.visibility = View.GONE
        }

        viewModel = ViewModelProvider(this).get(LoginSignUpVM::class.java)

        binding.tvGoToSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.ivBackArrow.setOnClickListener{
            onBackPressed()
        }
        binding.btnSignUp.setOnClickListener {
            condition()
        }
    }

    fun condition() {
        val email = binding.etSignUp.text.toString()
        val emailData = SendEmailResponse(email)
        val bundle = Bundle()
        bundle.putString("email", email)
        viewModel.sendEmail(emailData)
        val intent = Intent(this, ConfirmOTPActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)

//        viewModel.isErrorState().observe(this, { aBoolean: Boolean? ->
//            if (aBoolean == null) return@observe
//            if (aBoolean) {
//                binding.tvError.visibility = View.VISIBLE
//                return@observe
//            } else {
//                val intent = Intent(this, ConfirmOTPActivity::class.java)
//                intent.putExtras(bundle)
//                startActivity(intent)
//            }
//        })
    }

}