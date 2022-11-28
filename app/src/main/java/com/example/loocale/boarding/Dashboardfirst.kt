package com.example.loocale.boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import com.example.loocale.Helper
import com.example.loocale.R
import com.example.loocale.databinding.FragmentDashboardfirstBinding


class Dashboardfirst : Fragment() {

    private var _binding: FragmentDashboardfirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = LayoutInflater.from(container?.context)
            .inflate(R.layout.fragment_dashboardfirst, container, false)

        _binding = FragmentDashboardfirstBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.rlBoardingboxplus?.setOnClickListener {
            binding.rlBoardingboxplus.visibility = View.GONE
            binding.rlBoardingBoxMinus.visibility = View.VISIBLE
            binding.rlBoardingbigbox.visibility = View.VISIBLE
        }

        binding.rlBoardingBoxMinus?.setOnClickListener {
            binding.rlBoardingboxplus.visibility = View.VISIBLE
            binding.rlBoardingBoxMinus.visibility = View.GONE
            binding.rlBoardingbigbox.visibility = View.INVISIBLE
        }
        Helper.setColorSpan(binding.tvBoardingTitle,"Selamat Datang di Loocale!", "Loocale!", resources.getColor(R.color.loocale_secondary))
    }

    companion object {
        fun newInstance() {
        }
    }
}