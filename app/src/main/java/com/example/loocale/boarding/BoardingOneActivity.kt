package com.example.loocale.boarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.loocale.MainActivity
import com.example.loocale.R
import com.example.loocale.databinding.ActivityBoardingOneBinding

class BoardingOneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardingOneBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding_one)
        binding = ActivityBoardingOneBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupViewPager()
        setCondition2()
        buttonClick()
        }

    private fun setupViewPager() {
        val viewPager = binding.vpDashboard
        viewPager.adapter = BoardingAdapter(supportFragmentManager)
    }

    fun buttonClick() {
        binding.ivNextBoardingOne.setOnClickListener {
            binding.vpDashboard.setCurrentItem(binding.vpDashboard.currentItem+1, true)
        }
        binding.ivPrevoiusBoardingOne.setOnClickListener {
            binding.vpDashboard.setCurrentItem(binding.vpDashboard.currentItem-1, true)
        }
        binding.rlStart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun setCondition2() {
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
