package com.example.loocale.splashscreen

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.loocale.boarding.OnBoardingActivity
import com.example.loocale.loginsignup.FirstSignUpActivity
import com.example.loocale.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        checkFirstRun()

        binding.mlSplash.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(motionLayout: MotionLayout, i: Int, i1: Int) {
                ObjectAnimator.ofFloat(binding.motionLayout, View.ALPHA, 2f, 0f).setDuration(6000).start()
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout,
                i: Int,
                i1: Int,
                v: Float
            ) {
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout, i: Int) {

            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout,
                i: Int,
                b: Boolean,
                v: Float
            ) {
            }
        })
    }

    private fun checkFirstRun() {
        val isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .getBoolean("isFirstRun", true)
            val splashTime: Long = 3000

            Handler().postDelayed({
                if (!isFirstRun) {
                    val intent = Intent(this, FirstSignUpActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this, OnBoardingActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }, splashTime)

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false)
            .apply()
    }
}