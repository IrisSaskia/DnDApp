package com.example.dndapp.database.api

import com.example.dndapp.model.api.DnDApi
import com.example.dndapp.model.api.DnDApiService
import com.example.dndapp.model.api.dataClasses.BackgroundResult
import com.example.dndapp.model.api.dataClasses.CClassResult
import com.example.dndapp.model.api.dataClasses.RaceResult
import retrofit2.Call

class DnDApiRepository {
    private val dndApi: DnDApiService = DnDApi.createApi()

    //API requests for race
    fun getRaceNames(): Call<RaceResult> {
        return dndApi.getRaceNames()
    }
    fun getRace(name: String): Call<RaceResult> {
        return dndApi.getRace(name)
    }

    //API requests for cclass
    fun getCClassNames(): Call<CClassResult> {
        return dndApi.getCClassNames()
    }
    fun getCClass(name: String): Call<CClassResult> {
        return dndApi.getCClass(name)
    }

    //API requests for background
    fun getBackgroundNames(): Call<BackgroundResult> {
        return dndApi.getBackgroundNames()
    }
    fun getBackground(name: String): Call<BackgroundResult> {
        return dndApi.getBackground(name)
    }
}