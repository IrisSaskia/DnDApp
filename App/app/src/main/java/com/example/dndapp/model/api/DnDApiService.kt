package com.example.dndapp.model.api

import com.example.dndapp.model.api.dataClasses.BackgroundResult
import com.example.dndapp.model.api.dataClasses.CClassResult
import com.example.dndapp.model.api.dataClasses.RaceResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface DnDApiService {
    @GET("/races/?format=json")
    fun getRaceNames(): Call<RaceResult>

    // The GET method needed to retrieve the race info for the character
    @GET("/races/?format=json")
    fun getRace(
        @Query("name") name: String?
    ): Call<RaceResult>

    @GET("/classes/?format=json")
    fun getCClassNames(): Call<CClassResult>

    @GET("/backgrounds/?format=json")
    fun getBackgroundNames(): Call<BackgroundResult>

    // The GET method needed to retrieve the background info for the character
    @GET("/backgrounds/?format=json")
    fun getBackground(
        @Query("name") name: String?
    ): Call<BackgroundResult>
}