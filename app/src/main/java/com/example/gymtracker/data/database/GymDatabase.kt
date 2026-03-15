package com.example.gymtracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gymtracker.data.dao.ExerciseDao
import com.example.gymtracker.data.dao.MuscleDao
import com.example.gymtracker.data.dao.WeightDao
import com.example.gymtracker.data.entities.Exercise
import com.example.gymtracker.data.entities.Muscle
import com.example.gymtracker.data.entities.WeightEntry

@Database(
    entities = [Muscle::class, Exercise::class, WeightEntry::class],
    version = 2,
    exportSchema = false
)
abstract class GymDatabase : RoomDatabase() {

    abstract fun muscleDao(): MuscleDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun weightDao(): WeightDao

    companion object {
        @Volatile
        private var INSTANCE: GymDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "CREATE TABLE IF NOT EXISTS `weight_entries` (" +
                            "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "`weightKg` REAL NOT NULL, " +
                            "`date` TEXT NOT NULL)"
                )
            }
        }

        fun getDatabase(context: Context): GymDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    GymDatabase::class.java,
                    "gym_database"
                )
                .addMigrations(MIGRATION_1_2)
                .build().also { INSTANCE = it }
            }
        }
    }
}
