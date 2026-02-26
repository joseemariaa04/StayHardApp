package com.example.gymtracker.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises",
    foreignKeys = [ForeignKey(
        entity = Muscle::class,
        parentColumns = ["id"],
        childColumns = ["muscleId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("muscleId")]
)
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val muscleId: Long,
    val name: String,
    val weightKg: Float,
    val reps: Int,
    val lastModifiedDate: String // ISO format: yyyy-MM-dd
)
