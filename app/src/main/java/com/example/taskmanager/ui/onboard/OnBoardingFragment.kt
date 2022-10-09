package com.example.taskmanager.ui.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.taskmanager.R
import com.example.taskmanager.data.Preference
import com.example.taskmanager.databinding.FragmentOnBoardingBinding
import com.example.taskmanager.ui.onboard.adapter.OnBoardAdapter
import me.relex.circleindicator.CircleIndicator3

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = OnBoardAdapter(){
            Preference(requireContext()).showBoarding()
            if (auth.currentUser != null) {
                findNavController().navigateUp()
            } else {
                findNavController().navigate(R.id.authFragment)
            }

        }
        binding.onBoarding.adapter = adapter

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.onBoarding)

        binding.circleIndicator.attachToRecyclerView(binding.onBoarding, pagerSnapHelper)
        adapter.registerAdapterDataObserver(binding.circleIndicator.adapterDataObserver)

    }

}