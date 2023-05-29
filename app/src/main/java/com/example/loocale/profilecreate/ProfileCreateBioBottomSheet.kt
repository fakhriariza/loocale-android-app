package com.example.loocale.profilecreate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.loocale.loginsignup.LoginSignUpVM
import com.example.loocale.databinding.BasebotoomsheetbioBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ProfileCreateBioBottomSheet : BottomSheetDialogFragment() {
    private lateinit var viewModel: LoginSignUpVM
    private lateinit var _binding: BasebotoomsheetbioBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(LoginSignUpVM::class.java)
        _binding = BasebotoomsheetbioBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.tvDismiss.setOnClickListener{
            this.dismiss()
        }
    }

    companion object {
        fun newInstance() {
        }
    }
}