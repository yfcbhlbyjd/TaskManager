package com.example.taskmanager.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentAuthBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.PHONE_SIGN_IN_METHOD
import com.google.firebase.auth.PhoneAuthProvider.verifyPhoneNumber
import java.util.concurrent.TimeUnit


class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private var verificationId: String? = null
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSendPhone.setOnClickListener {
            if (binding.etPhoneNumber.text?.isNotEmpty() == true)
                sendPhoneNumber()
        else {
            binding.etPhoneNumber.error = getString(R.string.input_phone_number)
        }
    }
        binding.btnAccept.setOnClickListener {
            if (binding.etCode.text?.isNotEmpty() == true) {
                sendCode()
            } else {
                binding.etCode.error = getString(R.string.input_code)
            }
        }
}


    private fun sendPhoneNumber() {

        auth.setLanguageCode("ru")
        val options: PhoneAuthOptions = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+996${binding.etPhoneNumber.text.toString()}")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setCallbacks(object :  PhoneAuthProvider.OnVerificationStateChangedCallbacks {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    showToast(p0.message.toString())
                }
                override fun onCodeSent(verifId: String, p1: PhoneAuthProvider.ForceResendingToken){
                    binding.btnSendPhone.isVisible = false
                    binding.inputPhone.isVisible = false
                    binding.inputCode.isVisible = true
                    binding.btnAccept.isVisible = true
                    verificationId = verifId
                }
            }
            )
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    private fun sendCode(){
        val credential = verificationId?.let {
            PhoneAuthProvider.getCredential(
                it, binding.etCode.toString()) }
        if (credential != null) {
            signInWithPhoneAuthCredential(credential)
        }

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential){
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    showToast(" You have successfully registered ")
                    val user = task.result?.user
                    findNavController().navigate(R.id.navigation_home)
                } else { showToast()
                    if (task.exception is FirebaseAuthInvalidCredentialsException)
                        showToast((task.exception as FirebaseAuthInvalidCredentialsException).message.toString())
                }

            }
    }

}


