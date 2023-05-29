package com.example.loocale.profilecreate

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.example.loocale.loginsignup.LoginSignUpVM
import com.example.loocale.createCustomTempFile
import com.example.loocale.databinding.BasebotoomsheetupdatephotoBinding
import com.example.loocale.uriToFile
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.File


class ProfileCreateUploadPhotoBottomSheet : BottomSheetDialogFragment() {
    private var selectedImageUri: Uri? = null
    private lateinit var viewModel: LoginSignUpVM
    private lateinit var _binding: BasebotoomsheetupdatephotoBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(LoginSignUpVM::class.java)
        _binding = BasebotoomsheetupdatephotoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
        binding.tvDismiss.setOnClickListener{
            this.dismiss()
            val profileCreateBottomSheet = ProfileCreateBioBottomSheet()
            profileCreateBottomSheet.show(requireActivity().supportFragmentManager, "")
        }
        binding.btnSignUp.setOnClickListener{
            this.dismiss()
            val profileCreateBottomSheet = ProfileCreateBioBottomSheet()
            profileCreateBottomSheet.show(requireActivity().supportFragmentManager, "")
        }
        binding.ivAvatar.setOnClickListener{
            startTakePhoto()
//            val intent = Intent(context, CameraActivity::class.java)
//            startActivity(intent)
        }
        binding.tvGalery.setOnClickListener{
            startGallery()
        }
        binding.ivPreview.setOnClickListener{
            startTakePhoto()
        }
    }

//    private fun imageChooser() {
//        Intent(Intent.ACTION_PICK).also {
//            it.type = "image/*"
//            val imageType = arrayOf("image/jpeg", "image/jpg", "image/png")
//            it.putExtra(Intent.EXTRA_MIME_TYPES, imageType)
//            startActivityForResult(it,REQUEST_CODE_IMAGE)
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == Activity.RESULT_OK){
//            when (requestCode){
//                REQUEST_CODE_IMAGE -> {
//                    selectedImageUri = data?.data
//                    binding.cvPreview.visibility = View.VISIBLE
//                    binding.ivPreview.setImageURI(selectedImageUri)
//                }
//            }
//        }
//    }

    companion object {
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            binding.ivAvatar.visibility = View.GONE
            binding.ivPreview.visibility = View.VISIBLE
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, requireContext())
            binding.ivPreview.setImageURI(selectedImg)
        }
    }

    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(requireContext().packageManager)

        createCustomTempFile(requireContext()).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireContext(),
                "com.example.loocale.boarding",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private lateinit var currentPhotoPath: String
    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == AppCompatActivity.RESULT_OK) {
            binding.ivAvatar.visibility = View.GONE
            binding.ivPreview.visibility = View.VISIBLE
            val myFile = File(currentPhotoPath)

            val result =  BitmapFactory.decodeFile(myFile.path)
//            Silakan gunakan kode ini jika mengalami perubahan rotasi
//            val result = rotateBitmap(
//                BitmapFactory.decodeFile(myFile.path),
//                true
//            )

            binding.ivPreview.setImageBitmap(result)
        }
    }
}