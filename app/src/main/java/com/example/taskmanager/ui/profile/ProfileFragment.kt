package com.example.taskmanager.ui.profile

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.example.taskmanager.data.Preference
import com.example.taskmanager.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var preference: Preference(requireContext())
    private lateinit var binding: FragmentProfileBinding
    private val intentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK)
                binding.ivImage.setImageURI(result.data?.data)
                preference.setImageView(image.toString()
        }
    var mGetContent = registerForActivityResult<String>, Uri> (
        ActivityResultContracts.GetContent()
    ) { uri ->
        preference.setEditText(binding.profileText.toString())
        preference.setImageView(uri.toString())
        Glide.with(requireContext()).load(uri.toString()).into(binding.profileImage)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preference = Preference(requireContext())
        binding.profileText.setText(preference.getEditText());
        Glide.with(requireContext()).load(preference.getImageView()).into(binding.profileImage)

        binding.profileImage.setOnClickListener {
            mGetContent.launch("animation/*");
        }

        binding.profileText.addTextChangedListener{
            preference.setEditText(binding.profileText.text.toString())
        }
//
//        if (preference.getImageView() != null){
//            binding.animation.GlideYu(preference.getImageView()!!)
//        }
//        Glide.with(requireContext()).load(preference.getProfileImage()).into(binding.profileImage)
//        binding.profileImage.setOnClickListener {
//            mGetContent.launch("animation/*"):
//        }
//        binding.etText.addTextChangedListener {
//            preference.setEditText(binding.etText.toString())
//        }
//
//        override fun onDestroy() {
//            preference.saveEditText(binding.etSave.text.toString());
//            super.onDestroy()
//        }

    }
}