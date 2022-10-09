package com.example.taskmanager.ui.onboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ItemOnBoardingPageBinding
import com.example.taskmanager.loadImage
import com.example.taskmanager.ui.onboard.OnBoard

class OnBoardAdapter(private val onClick: ()->Unit) : RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder>() {
    private val arrayListBoarding = arrayListOf<OnBoard>()

    init {
        arrayListBoarding.add(
            OnBoard(
                R.raw.pencil_connecting_dots,
                "Hello",
                "Well come"

            )
        )
        arrayListBoarding.add(
            OnBoard(
                R.raw.design_concept,
                "Our App",
                "For an idea"

            )
        )
        arrayListBoarding.add(
            OnBoard(
                R.raw.energy_rocket,
                "Thanks",
                "For choosing"

            )
        )
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardViewHolder {
        return OnBoardViewHolder(
            ItemOnBoardingPageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        holder.bind(arrayListBoarding[position])
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class OnBoardViewHolder(private val binding: ItemOnBoardingPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(onBoard: OnBoard) {
            binding.tvSkip.setOnClickListener {
                onClick()
            }

            binding.btnStart.setOnClickListener {
                onClick()
            }

            binding.tvSkip.isVisible = adapterPosition != arrayListBoarding.size - 1
            binding.btnStart.isVisible = adapterPosition == arrayListBoarding.size - 1
            binding.tvTitle.text = onBoard.title
            binding.tvDesc.text = onBoard.description
            onBoard.animation?.let {
                binding.imagePager.setAnimation(it)

            }


        }
    }
}