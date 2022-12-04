package com.example.loocale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loocale.databinding.ActivityMainBinding
import com.example.loocale.databinding.ActivitySplashScreenBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(R.layout.activity_main)
        binding()
        showBottomSheet()

        binding.tvTitle.setOnClickListener{
        }
    }
    fun binding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun showBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(R.layout.basebotoomsheet)
        bottomSheetDialog.show()
    }
}