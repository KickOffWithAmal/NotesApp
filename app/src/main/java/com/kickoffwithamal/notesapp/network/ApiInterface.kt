package com.kickoffwithamal.notesapp.network

import com.kickoffwithamal.notesapp.network.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/v1/current.json")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") place: String
    ): Response<WeatherResponse>

}