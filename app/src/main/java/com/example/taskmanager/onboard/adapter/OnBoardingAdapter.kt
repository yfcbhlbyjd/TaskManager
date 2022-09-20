package com.example.taskmanager.onboard.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.taskmanager.onboard.OnBoard
import com.example.taskmanager.onboard.OnBoardingPageFragment

class OnBoardingAdapter(fn: FragmentManager): FragmentStatePagerAdapter(fn) {

    private val arrayListBoarding= arrayListOf(
        OnBoard("https://png.pngtree.com/png-vector/20190720/ourlarge/pngtree-task-icon-for-your-project-png-image_1552726.jpg", "Hello", "Well come"),
        OnBoard("https://png.pngtree.com/png-vector/20190720/ourlarge/pngtree-task-icon-for-your-project-png-image_1552726.jpg", "Hello", "Well come"),
        OnBoard("https://png.pngtree.com/png-vector/20190720/ourlarge/pngtree-task-icon-for-your-project-png-image_1552726.jpg", "Hello", "Well come")
    )
    override fun getCount(): Int = arrayListBoarding.size


    override fun getItem(position: Int): Fragment {
        val data = bundleOf(OnBoardingPageFragment.KEY_FOR_BOARD to arrayListBoarding[position])
        val fragment = OnBoardingPageFragment()
        fragment.arguments= data
        return fragment
        return OnBoardingPageFragment()

    }

}