package com.example.room_database_exercise.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room_database_exercise.model.Project

@Database(entities = [Project::class], version = 1, exportSchema = false)
abstract class ProjectDatabase: RoomDatabase() {

    abstract fun projectDao(): ProjectsDAO

    companion object {
        @Volatile
        private var INSTANCE: ProjectDatabase? = null

        fun getDatabase(context: Context): ProjectDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProjectDatabase::class.java,
                    "projects"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}