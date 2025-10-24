package com.praktikum.responsi1mobileh1d023001.data.model

import com.google.gson.annotations.SerializedName

data class Coach(
    @SerializedName(value = "name")
    val name: String?,
    @SerializedName(value = "dateOfBirth")
    val dateOfBirth: String?,
    @SerializedName(value = "nationality")
    val nationality: String?
)
