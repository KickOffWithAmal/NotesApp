package com.kickoffwithamal.notesapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kickoffwithamal.notesapp.network.ApiInterface
import com.kickoffwithamal.notesapp.network.NetworkManager
import com.kickoffwithamal.notesapp.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val apiInterface: ApiInterface): ViewModel() {

//    private val apiInterface = NetworkManager.apiInterface
    fun getWeatherData(place: String) {
        viewModelScope.launch {
            val response = apiInterface.getWeather(apiKey = Constant.apiKey,  place = place)
            if (response.isSuccessful) {
                Log.i("Response", response.body().toString())
            } else {
                Log.i("Response", response.message())
            }
        }
    }
}