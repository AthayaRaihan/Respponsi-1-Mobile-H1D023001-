package com.praktikum.responsi1mobileh1d023001.data.model

import com.google.gson.annotations.SerializedName
import com.praktikum.responsi1mobileh1d023001.model.Player

data class Club(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("venue")
    val venue: String?,
    @SerializedName("crest")
    val crest: String?,
    @SerializedName("coach")
    val coach: Coach?,
    @SerializedName("squad")
    val squad: List<Player>?
)
