package com.example.gymtracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymtracker.data.database.GymDatabase
import com.example.gymtracker.data.entities.WeightEntry
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WeightViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = GymDatabase.getDatabase(application).weightDao()

    val weightEntries = dao.getAllWeightEntries().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun addWeightEntry(weight: Float) {
        viewModelScope.launch {
            val date = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
            dao.insertWeightEntry(WeightEntry(weightKg = weight, date = date))
        }
    }

    fun deleteWeightEntry(entry: WeightEntry) {
        viewModelScope.launch {
            dao.deleteWeightEntry(entry)
        }
    }
}
