package com.example.loocale.boarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.loocale.R

class Dashboardone : Fragment() {

    private var twoFragment: Dashboardtwo? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        twoFragment = Dashboardtwo()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboardone, container, false)
    }

    companion object {
        fun newInstance() {

        }

    }
}