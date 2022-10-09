package com.example.taskmanager.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.taskmanager.R
import com.example.taskmanager.data.Preference
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRegistrar


class ProfileFragment : Fragment() {
    private lateinit var preference: Preference
    private lateinit var binding: FragmentProfileBinding

    var mGetContent = registerForActivityResult<String, Uri>(
        ActivityResultContracts.GetContent()
    ) { uri ->
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
        this.preference = Preference(requireContext())
        binding.profileText.setText(preference.getEditText());
        Glide.with(requireContext()).load(preference.getImageView()).into(binding.profileImage)
        binding.profileAge.setText(preference.getProfileAge())
        binding.profileImage.setOnClickListener {
            mGetContent.launch("animation/*");
        }

        binding.profileText.addTextChangedListener{
            preference.setEditText(binding.profileText.text.toString())
        }
            binding.profileAge.addTextChangedListener {
                preference.setProfileAge(binding.profileAge.text.toString())
            }

        binding.btnExit.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_profileFragment_to_authFragment)
        }

    }
}