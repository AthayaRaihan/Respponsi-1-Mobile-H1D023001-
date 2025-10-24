package com.praktikum.responsi1mobileh1d023001.ui.coach

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.praktikum.responsi1mobileh1d023001.R
import com.praktikum.responsi1mobileh1d023001.databinding.ActivityCoachBinding
import kotlinx.coroutines.launch

class CoachActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoachBinding
    private val viewModel: CoachViewModel by viewModels()

    // Ganti dengan token kamu sendiri dari football-data.org
    private val apiToken = "d0a6b3e11ce2415b857b7c20ad171a8d"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari API
        viewModel.fetchCoach(apiToken)

        // Observasi data dan tampilkan ke layout
        lifecycleScope.launch {
            viewModel.coach.collect { coach ->
                if (coach != null) {
                    binding.coachName.text = coach.name
                    binding.coachBirthDate.text = "${coach.dateOfBirth}"
                    binding.coachNationality.text = "${coach.nationality}"
                }
            }
        }
    }
}
