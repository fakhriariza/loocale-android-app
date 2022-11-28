package com.example.loocale

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.loocale.boarding.BoardingOneActivity
import com.example.loocale.boarding.SingUpFirstActivity
import com.example.loocale.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        checkFirstRun()
        binding()

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

    fun binding() {
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun checkFirstRun() {
        val isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .getBoolean("isFirstRun", true)
            val splashTime: Long = 3000

            Handler().postDelayed({
                if (!isFirstRun) {
                    val intent = Intent(this, SingUpFirstActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this, BoardingOneActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }, splashTime)

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false)
            .apply()
    }
}