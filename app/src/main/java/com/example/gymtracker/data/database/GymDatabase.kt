package com.example.gymtracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gymtracker.data.dao.ExerciseDao
import com.example.gymtracker.data.dao.MuscleDao
import com.example.gymtracker.data.entities.Exercise
import com.example.gymtracker.data.entities.Muscle

@Database(entities = [Muscle::class, Exercise::class], version = 1, exportSchema = false)
abstract class GymDatabase : RoomDatabase() {

    abstract fun muscleDao(): MuscleDao
    abstract fun exerciseDao(): ExerciseDao

    companion object {
        @Volatile
        private var INSTANCE: GymDatabase? = null

        fun getDatabase(context: Context): GymDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    GymDatabase::class.java,
                    "gym_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
