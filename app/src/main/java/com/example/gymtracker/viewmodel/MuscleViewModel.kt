package com.example.gymtracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymtracker.data.database.GymDatabase
import com.example.gymtracker.data.entities.Muscle
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MuscleViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = GymDatabase.getDatabase(application).muscleDao()

    val muscles = dao.getAllMuscles().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun addMuscle(name: String) {
        viewModelScope.launch {
            dao.insertMuscle(Muscle(name = name.trim()))
        }
    }

    fun deleteMuscle(muscle: Muscle) {
        viewModelScope.launch {
            dao.deleteMuscle(muscle)
        }
    }
}
