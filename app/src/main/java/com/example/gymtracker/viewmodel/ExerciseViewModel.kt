package com.example.gymtracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymtracker.data.database.GymDatabase
import com.example.gymtracker.data.entities.Exercise
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = GymDatabase.getDatabase(application).exerciseDao()

    private val muscleIdFlow = MutableStateFlow(-1L)

    @OptIn(ExperimentalCoroutinesApi::class)
    val exercises = muscleIdFlow.flatMapLatest { id ->
        dao.getExercisesForMuscle(id)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun setMuscleId(id: Long) {
        muscleIdFlow.value = id
    }

    fun addExercise(muscleId: Long, name: String, weightKg: Float, reps: Int) {
        viewModelScope.launch {
            dao.insertExercise(
                Exercise(
                    muscleId = muscleId,
                    name = name.trim(),
                    weightKg = weightKg,
                    reps = reps,
                    lastModifiedDate = LocalDate.now().toString()
                )
            )
        }
    }

    fun updateExercise(exercise: Exercise, newWeightKg: Float, newReps: Int) {
        viewModelScope.launch {
            dao.updateExercise(
                exercise.copy(
                    weightKg = newWeightKg,
                    reps = newReps,
                    lastModifiedDate = LocalDate.now().toString()
                )
            )
        }
    }

    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch {
            dao.deleteExercise(exercise)
        }
    }
}
