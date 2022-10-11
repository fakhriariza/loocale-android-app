package com.example.loocale.boarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loocale.R

class BoardingTwoActivity : AppCompatActivity() {

    val fm = supportFragmentManager
    private var oneFragment: Dashboardone? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding_two)

        oneFragment = Dashboardone()

        oneFragment?.let {
            fm.beginTransaction()
                .setCustomAnimations(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim)
                .replace(R.id.fcv_boardingtwo, it)
                .commitNow()

        }
    }
}