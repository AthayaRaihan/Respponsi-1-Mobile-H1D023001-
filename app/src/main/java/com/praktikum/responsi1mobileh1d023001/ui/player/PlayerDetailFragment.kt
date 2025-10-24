package com.praktikum.responsi1mobileh1d023001.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.praktikum.responsi1mobileh1d023001.R

class PlayerDetailFragment(
    private val name: String,
    private val dateOfBirth: String,
    private val nationality: String,
    private val position: String
) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = view.findViewById<android.widget.ImageView>(R.id.imageViewCover)
        val tvName = view.findViewById<android.widget.TextView>(R.id.textViewName)
        val tvDob = view.findViewById<android.widget.TextView>(R.id.textViewDateOfBirth)
        val tvNationality = view.findViewById<android.widget.TextView>(R.id.textViewNationality)
        val tvPosition = view.findViewById<android.widget.TextView>(R.id.textViewPosition)

        // Populate
        tvName.text = name
        tvDob.text = dateOfBirth
        tvNationality.text = nationality
        tvPosition.text = position

        // Use the project person icon for all players
        imageView.setImageResource(R.drawable.ic_player)
    }
}
