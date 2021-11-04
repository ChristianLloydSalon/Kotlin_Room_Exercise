package com.example.room_database_exercise.repository

import androidx.lifecycle.LiveData
import com.example.room_database_exercise.data.ProjectsDAO
import com.example.room_database_exercise.model.Project

class ProjectRepository(private val projectDao: ProjectsDAO) {

    suspend fun addProject(project: Project) {
        projectDao.addProject(project)
    }

    fun getProjects(): LiveData<List<Project>> {
        return projectDao.getProjects()
    }

    suspend fun updateProject(project: Project) {
        projectDao.updateProject(project)
    }

    suspend fun deleteProject(project: Project) {
        projectDao.deleteProject(project)
    }

    suspend fun deleteAllProjects() {
        projectDao.deleteAllProjects()
    }
}