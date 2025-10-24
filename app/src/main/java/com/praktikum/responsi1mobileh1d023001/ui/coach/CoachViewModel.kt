package com.praktikum.responsi1mobileh1d023001.ui.coach

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praktikum.responsi1mobileh1d023001.data.model.Coach
import com.praktikum.responsi1mobileh1d023001.data.repository.CoachRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoachViewModel : ViewModel() {

	private val repository = CoachRepository()

	private val _coach = MutableStateFlow<Coach?>(null)
	val coach: StateFlow<Coach?> get() = _coach

	fun fetchCoach(token: String) {
		viewModelScope.launch {
			try {
				val response = repository.getCoach(token)
				if (response.isSuccessful) {
					_coach.value = response.body()?.coach
				} else {
					println("Error: ${response.code()} - ${response.message()}")
				}
			} catch (e: Exception) {
				e.printStackTrace()
			}
		}
	}
}
