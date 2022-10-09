package com.example.taskmanager.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            binding.btnSave.text = getString(R.string.update)
            task = arguments?.getSerializable("k_task") as Task
            binding.etTitle.setText(task?.title)
            binding.etDescription.setText(task?.description)
        } else {
            binding.btnSave.text = getString(R.string.save)

        }

        binding.btnSave.setOnClickListener {
            if (arguments != null) {
                task?.let { it1 -> update(it1) }
            } else {
                save()
            }

        }
    }

    private fun update(task: Task) {
        task.title = binding.etTitle.text.toString()
        task.description = binding.etDescription.text.toString()
        App.db.dao().update(task)


    }

    private fun save() {
        App.db.dao().insert(
            Task(
                title = binding.etTitle.text.toString(),
                description = binding.etDescription.text.toString()
            )
        )

        findNavController().navigateUp()

    }


}