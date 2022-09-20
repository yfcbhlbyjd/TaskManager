package com.example.taskmanager.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentOnBoardingBinding
import com.example.taskmanager.databinding.FragmentOnBoardingPageBinding
import com.example.taskmanager.loadImage
import me.relex.circleindicator.CircleIndicator3


class OnBoardingPageFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingPageBinding
    private lateinit var bindingForCircleIndicator: FragmentOnBoardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardingPageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val data= it.getSerializable(KEY_FOR_BOARD) as OnBoard
            binding.tvTitle.text = data.title
            binding.tvDesc.text = data.description
            data.image?.let { it1 -> binding.imagePager.loadImage(it1) }
            binding.btnStart.isVisible = data.isLast == true
            binding.tvSkip.isVisible = data.isLast == false
            val indicator: CircleIndicator3 = bindingForCircleIndicator.circleIndicator
            indicator.setViewPager(bindingForCircleIndicator.onBoarding)

        }
       binding.btnStart.setOnClickListener {
           findNavController().navigate(R.id.navigation_home)
       }


    }


    companion object {
        const val KEY_FOR_BOARD = "board_item"
    }
}