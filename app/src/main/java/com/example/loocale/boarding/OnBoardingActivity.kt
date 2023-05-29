package com.example.loocale.boarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.loocale.R
import com.example.loocale.databinding.ActivityBoardingOneBinding
import com.example.loocale.loginsignup.FirstSignUpActivity

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBoardingOneBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBoardingOneBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)

        setupViewPager()
        setCondition2()
        buttonClick()
    }

    private fun setupViewPager() {
        val viewPager = binding.vpDashboard
        viewPager.adapter = BoardingAdapter(supportFragmentManager)
    }

    private fun buttonClick() {
        binding.ivNextBoardingOne.setOnClickListener {
            binding.vpDashboard.setCurrentItem(binding.vpDashboard.currentItem+1, true)
        }
        binding.ivPrevoiusBoardingOne.setOnClickListener {
            binding.vpDashboard.setCurrentItem(binding.vpDashboard.currentItem-1, true)
        }
        binding.rlStart.setOnClickListener {
            val intent = Intent(this, FirstSignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setCondition2() {
        binding.vpDashboard.setOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.ivPrevoiusBoardingOne.visibility = View.GONE
                        binding.ivNextBoardingOne.visibility = View.VISIBLE
                        binding.rlStart.visibility = View.GONE
                    }
                    1 -> {
                        binding.ivPrevoiusBoardingOne.visibility = View.VISIBLE
                        binding.ivNextBoardingOne.visibility = View.VISIBLE
                        binding.rlStart.visibility = View.GONE
                    }
                    2 -> {
                        binding.ivPrevoiusBoardingOne.visibility = View.VISIBLE
                        binding.ivNextBoardingOne.visibility = View.VISIBLE
                        binding.rlStart.visibility = View.GONE
                    }
                    3 -> {
                        binding.ivPrevoiusBoardingOne.visibility = View.GONE
                        binding.ivNextBoardingOne.visibility = View.GONE
                        binding.rlStart.visibility = View.VISIBLE
                    } else -> {
                        binding.ivPrevoiusBoardingOne.visibility = View.GONE
                        binding.ivNextBoardingOne.visibility = View.VISIBLE
                        binding.rlStart.visibility = View.GONE
                    }
                }
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageScrollStateChanged(state: Int) {
            }
        }

        )
    }
}
