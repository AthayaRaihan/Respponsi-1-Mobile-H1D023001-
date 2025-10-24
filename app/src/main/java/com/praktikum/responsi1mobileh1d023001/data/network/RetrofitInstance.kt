package com.praktikum.responsi1mobileh1d023001.data.network

import com.praktikum.responsi1mobileh1d023001.utils.Constanta
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: OpenLibraryApi by lazy{
        Retrofit.Builder()
            .baseUrl(Constanta.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenLibraryApi::class.java)
    }
}