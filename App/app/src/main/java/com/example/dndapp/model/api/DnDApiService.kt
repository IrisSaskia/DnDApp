package com.example.dndapp.model.api

import com.example.dndapp.model.api.dataClasses.Background
import retrofit2.http.GET
import retrofit2.Call

public interface DnDApiService {
    // The GET method needed to retrieve a random number trivia.
    @GET("/backgrounds/?format=json")
    fun getBackground(name: String): Call<Background>
}