package com.example.loocale

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loocale.profilecreate.CameraActivity
import com.example.loocale.databinding.ActivityMainBinding
import com.example.loocale.profilecreate.ProfileCreateBottomSheet

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(R.layout.activity_main)
        binding()

        binding.tvTitle.setOnClickListener{
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        if (intent.hasExtra("Username")) {
                intent.getStringExtra("Username").let {
                    username = it
                }
            }
        showBottomSheet()
        println("ada isinya gak $username")
    }

    fun binding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun showBottomSheet() {
        val profileCreateBottomSheetInstance = ProfileCreateBottomSheet.newInstance(username)
        println("ada isinya gak2 $username")
        profileCreateBottomSheetInstance.show(supportFragmentManager, "")

    }

}