package com.example.loocale.boarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class BoardingAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    private val COUNT = 4


    override fun getCount(): Int {
        return COUNT
    }

    override fun getItem(position: Int): Fragment {
        return  when (position) {
            1 -> OnBoardingDiscoverFragment()
            2 -> OnBoardingShareFragment()
            3 -> OnBoardingConnectFragment()
            else -> OnBoardingWelcomeFragment()
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }
}