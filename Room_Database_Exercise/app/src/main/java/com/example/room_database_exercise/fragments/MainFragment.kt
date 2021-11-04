package com.example.room_database_exercise.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_database_exercise.R
import com.example.room_database_exercise.databinding.FragmentMainBinding
import com.example.room_database_exercise.model.viewmodel.ProjectViewModel
import com.example.room_database_exercise.utils.RecyclerAdapter

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var projectViewModel: ProjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Add menu
        setHasOptionsMenu(true)

        // Recyclerview
        val adapter = RecyclerAdapter()
        val recyclerView = binding.myRecyclerView

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ProjectViewModel
        projectViewModel = ViewModelProvider(this)[ProjectViewModel::class.java]
        projectViewModel.readAllData.observe( viewLifecycleOwner, { project ->
                adapter.setData(project)
            }
        )

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete)
            deleteAllProject()
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllProject() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            projectViewModel.deleteAllProjects()
            Toast.makeText(requireContext(), "Successfully removed all projects!", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("No") { _, _ -> }

        builder.setTitle("Delete All?")
        builder.setMessage("Are you sure you want to delete all projects?")
        builder.create().show()
    }
}