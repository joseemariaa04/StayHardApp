package com.example.gymtracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gymtracker.data.entities.Muscle
import kotlinx.coroutines.flow.Flow

@Dao
interface MuscleDao {
    @Query("SELECT * FROM muscles ORDER BY name ASC")
    fun getAllMuscles(): Flow<List<Muscle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMuscle(muscle: Muscle): Long

    @Delete
    suspend fun deleteMuscle(muscle: Muscle)
}
