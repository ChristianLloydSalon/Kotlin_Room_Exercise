package com.example.room_database_exercise.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room_database_exercise.data.ProjectDatabase
import com.example.room_database_exercise.repository.ProjectRepository
import com.example.room_database_exercise.model.Project
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProjectViewModel(application: Application): AndroidViewModel(application) {
    var readAllData: LiveData<List<Project>>
    private val repository: ProjectRepository

    init {
        val projectDao = ProjectDatabase.getDatabase(application).projectDao()
        repository = ProjectRepository(projectDao)
        readAllData = repository.getProjects()
    }

    // Add new project
     fun addProject(project: Project) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProject(project)
        }
    }

    // Update a project
    fun updateProject(project: Project) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProject(project)
        }
    }

    // Delete a project
    fun deleteProject(project: Project) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProject(project)
        }
    }

    // Delete all projects
    fun deleteAllProjects() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllProjects()
        }
    }
}