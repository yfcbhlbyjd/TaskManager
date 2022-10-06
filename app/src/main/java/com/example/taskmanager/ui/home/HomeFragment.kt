package com.example.taskmanager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var  adapter: TaskAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(
            requireContext(),
            this:: onLongClick,
            this:: onClick
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        return root
    }

    private fun onClick(pos: Int){
        val task = adapter.getTask(pos)
        findNavController().navigate(R.id.taskFragment, bundleOf("k_task" to task))

    }

    private fun onLongClick(pos: Int){
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Delete?")
        alertDialog.setPositiveButton("Yes"){dialog, _ ->
            App.db.dao().delete(adapter.getTask(pos))
            setData()
            dialog.dismiss()
        }
        alertDialog.setNegativeButton("No"){dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.create().show()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
        binding.recycler.adapter = adapter
        setData()

    }

    private fun setData(){
        val list = App.db.dao().getAllTask()
        adapter.addTasks(list.reversed())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}