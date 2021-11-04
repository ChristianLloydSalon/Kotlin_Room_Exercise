package com.example.room_database_exercise.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate = true)
    val ID: Int,
    val title: String,
    val description: String,
): Parcelable
