package com.example.room_database_exercise.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import com.example.room_database_exercise.model.Project

@Dao
interface ProjectsDAO {
    @Query("SELECT * FROM projects")
    fun getProjects(): LiveData<List<Project>>

    @Update
    suspend fun updateProject(project: Project)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProject(project: Project)

    @Delete
    suspend fun deleteProject(project: Project)

    @Query("DELETE FROM projects")
    suspend fun deleteAllProjects()
}