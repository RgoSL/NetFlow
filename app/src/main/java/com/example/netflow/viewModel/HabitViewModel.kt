package com.example.netflow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.netflow.auth.data.models.Habit
import com.example.netflow.crud.data.HabitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HabitViewModel(
    private  val repository: HabitRepository = HabitRepository()
) : ViewModel() {

    private val _habits = MutableStateFlow<List<Habit>>(emptyList())
    val habits: StateFlow<List<Habit>> = _habits

    init {
        loadHabits()
    }

    private fun loadHabits() {
        repository.getHabit { habitList ->
            _habits.value = habitList
        }
    }

    fun addHabit(habit: Habit, onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            repository.addHabit(habit) { success ->
                if (success) onComplete()
            }
        }
    }

    fun updateHabit(habit: Habit, onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            repository.updateHabit(habit) { success ->
                if (success) onComplete()
            }
        }
    }

    fun deleteHabit(habitId: String, onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            repository.deleteHabit(habitId) { success ->
                if (success) onComplete()
            }
        }
    }

    fun getById(id: String): Habit? {
        return habits.value.find { it.id == id }
    }
}