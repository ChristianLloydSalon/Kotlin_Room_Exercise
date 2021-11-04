package com.example.room_database_exercise.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room_database_exercise.R
import com.example.room_database_exercise.databinding.FragmentAddBinding
import com.example.room_database_exercise.model.Project
import com.example.room_database_exercise.model.viewmodel.ProjectViewModel

class AddFragment : Fragment() {
    private lateinit var projectViewModel: ProjectViewModel
    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        projectViewModel = ViewModelProvider(this)[ProjectViewModel::class.java]

        binding.addButton.setOnClickListener {
            insertNewProject()
        }
    }

    private fun insertNewProject() {
        val projectTitle = binding.projectTitleInput.text.toString()
        val projectDescription = binding.projectDescriptionInput.text.toString()

        if (validateInput(projectTitle, projectDescription)) {
            // Create new project
            val project = Project(0, projectTitle, projectDescription)

            // Add project to database
            projectViewModel.addProject(project)

            // Navigate back to List Fragment
            findNavController().navigate(R.id.action_addFragment_to_mainFragment)
        }
    }

    private fun validateInput(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }
}