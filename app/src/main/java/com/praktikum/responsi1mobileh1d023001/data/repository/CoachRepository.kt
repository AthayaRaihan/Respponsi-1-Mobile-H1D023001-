package com.praktikum.responsi1mobileh1d023001.data.repository

import com.praktikum.responsi1mobileh1d023001.data.model.Club
import com.praktikum.responsi1mobileh1d023001.data.network.RetrofitInstance
import com.praktikum.responsi1mobileh1d023001.utils.Constanta
import retrofit2.Response

class CoachRepository {
    suspend fun getCoach(token: String): Response<Club> {
        return RetrofitInstance.api.getTeam(Constanta.TEAM_ID, token)
    }
}
