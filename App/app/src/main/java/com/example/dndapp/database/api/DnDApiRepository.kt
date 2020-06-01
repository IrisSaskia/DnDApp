package com.example.dndapp.database.api

import com.example.dndapp.model.api.DnDApi
import com.example.dndapp.model.api.DnDApiService
import com.example.dndapp.model.api.dataClasses.Result
import retrofit2.Call

class DnDApiRepository {
    private val dndApi: DnDApiService = DnDApi.createApi()

    fun getBackground(name: String): Call<Result> {
        return dndApi.getBackground(name)
    }
}