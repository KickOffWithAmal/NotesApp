package com.kickoffwithamal.notesapp.network.model

data class WeatherResponse(
    val current: Current,
    val location: Location
)