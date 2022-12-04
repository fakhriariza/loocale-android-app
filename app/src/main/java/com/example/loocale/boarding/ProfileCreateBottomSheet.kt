package com.example.loocale.boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import com.example.loocale.Helper
import com.example.loocale.R
import com.example.loocale.databinding.BasebotoomsheetBinding
import com.example.loocale.databinding.FragmentDashboardfirstBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ProfileCreateBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BasebotoomsheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = LayoutInflater.from(container?.context)
            .inflate(R.layout.basebotoomsheet, container, false)

        _binding = BasebotoomsheetBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    companion object {
        fun newInstance() {
        }
    }
}