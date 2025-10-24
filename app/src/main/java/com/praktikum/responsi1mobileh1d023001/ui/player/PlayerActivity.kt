package com.praktikum.responsi1mobileh1d023001.ui.player

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.praktikum.responsi1mobileh1d023001.databinding.ActivityPlayerBinding
import kotlinx.coroutines.launch
import com.praktikum.responsi1mobileh1d023001.model.Player

class PlayerActivity : AppCompatActivity(), PlayerAdapter.OnPlayerClickListener {

    private lateinit var binding: ActivityPlayerBinding
    private val viewModel: PlayerViewModel by viewModels()
    private lateinit var adapter: PlayerAdapter

    private val apiToken = "d0a6b3e11ce2415b857b7c20ad171a8d"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView; pass `this` as click listener
        adapter = PlayerAdapter(emptyList(), this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Fetch data
        viewModel.fetchPlayers(apiToken)

        // Observe data
        lifecycleScope.launch {
            viewModel.players.collect { playerList ->
                adapter.updateData(playerList)
            }
        }
    }

    override fun onPlayerClick(player: Player) {
        // Show bottom sheet dialog with player details (similar to Book example)
        PlayerDetailFragment(
            name = player.name ?: "-",
            dateOfBirth = player.dateOfBirth ?: "-",
            nationality = player.nationality ?: "-",
            position = player.position ?: "-"
        ).show(supportFragmentManager, PlayerDetailFragment::class.java.simpleName)
    }
}
