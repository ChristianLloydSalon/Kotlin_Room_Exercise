package com.example.room_database_exercise.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room_database_exercise.R
import com.example.room_database_exercise.databinding.FragmentUpdateBinding
import com.example.room_database_exercise.model.Project
import com.example.room_database_exercise.model.viewmodel.ProjectViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var projectViewModel: ProjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Add menu
        setHasOptionsMenu(true)

        projectViewModel = ViewModelProvider(this)[ProjectViewModel::class.java]

        binding.projectTitle.setText(args.currentProject.title)
        binding.projectDescription.setText(args.currentProject.description)

        // set update button onclick event
        binding.updateButton.setOnClickListener {
            updateItem()
        }
    }

    private fun updateItem() {
        val title = binding.projectTitle.text.toString()
        val description = binding.projectDescription.text.toString()

        if (validateInput(title, description)) {
            // create new project
            val updatedProject = Project(args.currentProject.ID, title, description)

            // update database
            projectViewModel.updateProject(updatedProject)

            // Navigate back to Main Fragment
            findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
        }
    }

    // validate if inputs are not empty
    private fun validateInput(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete)
            deleteProject()
        return super.onOptionsItemSelected(item)
    }

    private fun deleteProject() {
        val projectTitle = args.currentProject.title
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            projectViewModel.deleteProject(args.currentProject)
            Toast.makeText(requireContext(), "Successfully removed $projectTitle!", Toast.LENGTH_SHORT).show()

            // Navigate back to Main Fragment
            findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
        }

        builder.setNegativeButton("No") { _, _ -> }

        builder.setTitle("Delete $projectTitle?")
        builder.setMessage("Are you sure you want to delete $projectTitle")
        builder.create().show()
    }
}