package com.praktikum.responsi1mobileh1d023001.data.repository

import com.praktikum.responsi1mobileh1d023001.data.network.RetrofitInstance
import com.praktikum.responsi1mobileh1d023001.utils.Constanta
import retrofit2.Response
import com.praktikum.responsi1mobileh1d023001.data.model.Club

class PlayerRepository {
    suspend fun getPlayers(token: String): Response<Club> {
        return RetrofitInstance.api.getTeam(Constanta.TEAM_ID, token)
    }
}
