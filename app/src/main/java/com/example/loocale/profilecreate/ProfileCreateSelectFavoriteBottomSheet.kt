package com.example.loocale.profilecreate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.loocale.loginsignup.LoginSignUpVM
import com.example.loocale.databinding.BasebotoomsheetselectfavoriteBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ProfileCreateSelectFavoriteBottomSheet : BottomSheetDialogFragment() {
    private lateinit var viewModel: LoginSignUpVM
    private lateinit var _binding: BasebotoomsheetselectfavoriteBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(LoginSignUpVM::class.java)
        _binding = BasebotoomsheetselectfavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnSignUp.setOnClickListener{
            this.dismiss()
            val profileCreateBottomSheet = ProfileCreateUploadPhotoBottomSheet()
            profileCreateBottomSheet.show(requireActivity().supportFragmentManager, "")
        }
        setAdapterData()
    }
    private fun setAdapterData () {
        viewModel.getConnectData()
        viewModel.connectData.observe(this) { response: ConnectResponse? ->
            if (response == null) return@observe
            val data = response.data
            val layoutmanager = GridLayoutManager(requireContext(), 2)
            println("isinya berapa ${data.size}")
            binding.gvSelectFavorite.layoutManager = layoutmanager
            binding.gvSelectFavorite.adapter = ConnectAdapter(requireContext(), data)
        }
    }

    companion object {
        fun newInstance() {
        }
    }
}