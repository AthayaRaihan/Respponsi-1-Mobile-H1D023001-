package com.praktikum.responsi1mobileh1d023001.ui.player

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.praktikum.responsi1mobileh1d023001.R
import com.praktikum.responsi1mobileh1d023001.model.Player

class PlayerAdapter(
    private var players: List<Player>,
    private val listener: OnPlayerClickListener
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    interface OnPlayerClickListener {
        fun onPlayerClick(player: Player)
    }

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvNationality: TextView = itemView.findViewById(R.id.tvNationality)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.tvName.text = player.name
        holder.tvNationality.text = player.nationality

        // Click
        holder.itemView.setOnClickListener {
            listener.onPlayerClick(player)
        }

        when {
            player.position?.contains("Goalkeeper", true) == true -> {
                holder.cardView.setCardBackgroundColor(holder.itemView.context.getColor(R.color.yellow))
            }

            player.position?.contains("Defence", true) == true ||
                    player.position?.contains("Defender", true) == true ||
                    player.position?.contains("Left-Back", true) == true ||
                    player.position?.contains("Centre-Back", true) == true ||
                    player.position?.contains("Right-Back", true) == true -> {
                holder.cardView.setCardBackgroundColor(holder.itemView.context.getColor(R.color.blue))
            }

            player.position?.contains("Midfield", true) == true ||
                    player.position?.contains("Attacking Midfield", true) == true ||
                    player.position?.contains("Central Midfield", true) == true ||
                    player.position?.contains("Defensive Midfield", true) == true -> {
                holder.cardView.setCardBackgroundColor(holder.itemView.context.getColor(R.color.green))
            }

            player.position?.contains("Offence", true) == true ||
                    player.position?.contains("Right Winger", true) == true ||
                    player.position?.contains("Left Winger", true) == true ||
                    player.position?.contains("Centre-Forward", true) == true -> {
                holder.cardView.setCardBackgroundColor(holder.itemView.context.getColor(R.color.red))
            }

            else -> {
                holder.cardView.setCardBackgroundColor(holder.itemView.context.getColor(R.color.white))
            }
        }

    }

    override fun getItemCount() = players.size

    fun updateData(newList: List<Player>) {
        players = newList
        notifyDataSetChanged()
    }
}
