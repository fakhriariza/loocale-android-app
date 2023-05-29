package com.example.loocale.profilecreate

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.loocale.loginsignup.LoginSignUpVM
import com.example.loocale.databinding.BasebotoomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ProfileCreateBottomSheet : BottomSheetDialogFragment() {
    private lateinit var viewModel: LoginSignUpVM
    private lateinit var _binding: BasebotoomsheetBinding
    private val binding get() = _binding
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(LoginSignUpVM::class.java)
        _binding = BasebotoomsheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getProvincesData()
        viewModel.provinceData.observe(this) { response: ProvincesResponse ->
            if (response == null) return@observe
            val data = response.data
            println("bentuknya gimana2 $data")
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, data)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spLocationprovince.adapter = adapter
        }
        if (arguments != null) {
            username = arguments?.getString(USERNAME)
            println("ada isinya gak3 $username")
        }
        binding.spLocationprovince.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val item = parent.getItemAtPosition(position)
                viewModel.getCitiesData(item.toString())
                viewModel.citiesData.observe(viewLifecycleOwner) { response: CitiesResponse ->
                    if (response == null) return@observe
                    val data = response.data
                    for (i in data.indices) {
                        val items = data[i].name
                        val listCities: List<String> = listOf(items)
                        println("bentuknya gimana3 $items")
                        println("bentuknya gimana1 $listCities")
                        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listCities)
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        binding.spLocationcities.adapter = adapter
                    }

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                parent.getItemAtPosition(0)
            }
        })
        binding.btnSignUp.setOnClickListener{
            this.dismiss()
            val profileCreateBottomSheet = ProfileCreateSelectFavoriteBottomSheet()
            profileCreateBottomSheet.show(requireActivity().supportFragmentManager, "")
        }
        binding.tvTitle.setText("Halo, $username!")
    }

    companion object {
        private const val USERNAME = "username"
        fun newInstance(userAccount: String?): ProfileCreateBottomSheet {
            println("ada isinya gak4 $userAccount")
            val fragment = ProfileCreateBottomSheet()
            val args = Bundle()
            args.putString(USERNAME, userAccount)
            fragment.arguments = args
            return fragment
        }
    }
}