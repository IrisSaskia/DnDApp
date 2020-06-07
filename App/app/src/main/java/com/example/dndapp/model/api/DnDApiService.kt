package com.example.dndapp.model.api

import com.example.dndapp.model.api.dataClasses.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface DnDApiService {
    // The GET method needed to retrieve the background info for the character
    @GET("/backgrounds/?format=json")
    fun getBackground(
        @Query("name") name: String?
    ): Call<Result>

    @GET("/backgrounds/?format=json")
    fun getBackgroundNames(): Call<Result>
}