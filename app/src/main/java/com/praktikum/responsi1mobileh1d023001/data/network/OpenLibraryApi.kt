package com.praktikum.responsi1mobileh1d023001.data.network

import com.praktikum.responsi1mobileh1d023001.data.model.Club
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface OpenLibraryApi {
	@GET("teams/{id}")
	suspend fun getTeam(
		@Path("id") id: String,
		@Header("X-Auth-Token") token: String
	): Response<Club>
}
