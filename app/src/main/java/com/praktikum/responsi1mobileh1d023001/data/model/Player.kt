package com.praktikum.responsi1mobileh1d023001.model

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName(value = "id")
    val id: Int?,
    @SerializedName(value = "name")
    val name: String?,
    @SerializedName(value = "position")
    val position: String?,
    @SerializedName(value = "dateOfBirth")
    val dateOfBirth: String?,
    @SerializedName(value = "nationality")
    val nationality: String?
)
