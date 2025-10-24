package com.praktikum.responsi1mobileh1d023001.ui.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praktikum.responsi1mobileh1d023001.data.model.Club
import com.praktikum.responsi1mobileh1d023001.data.repository.PlayerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayerViewModel : ViewModel() {

    private val repository = PlayerRepository()
    private val _players = MutableStateFlow<List<com.praktikum.responsi1mobileh1d023001.model.Player>>(emptyList())
    val players: StateFlow<List<com.praktikum.responsi1mobileh1d023001.model.Player>> get() = _players

    fun fetchPlayers(token: String) {
        viewModelScope.launch {
            try {
                val response = repository.getPlayers(token)
                if (response.isSuccessful) {
                    _players.value = response.body()?.squad ?: emptyList()
                } else {
                    println("Error fetching players: ${response.code()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
