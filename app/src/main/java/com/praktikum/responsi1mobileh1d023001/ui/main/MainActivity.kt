package com.praktikum.responsi1mobileh1d023001.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.praktikum.responsi1mobileh1d023001.R
import com.praktikum.responsi1mobileh1d023001.databinding.ActivityMainBinding
import com.praktikum.responsi1mobileh1d023001.ui.history.HistoryActivity
import com.praktikum.responsi1mobileh1d023001.ui.coach.CoachActivity
import com.praktikum.responsi1mobileh1d023001.ui.player.PlayerActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Initialize view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up click listeners for navigation
        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Navigate to History Activity
        binding.menuHistoryCard.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        // Navigate to Coach Activity
        binding.menuCoachCard.setOnClickListener {
            startActivity(Intent(this, CoachActivity::class.java))
        }

        // Navigate to Player Activity
        binding.menuSquadCard.setOnClickListener {
            startActivity(Intent(this, PlayerActivity::class.java))
        }
    }
}