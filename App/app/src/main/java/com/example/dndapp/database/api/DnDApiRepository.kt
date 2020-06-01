package com.example.dndapp.database.api

import com.example.dndapp.model.api.DnDApi
import com.example.dndapp.model.api.DnDApiService

class DnDApiRepository {
    private val dndApi: DnDApiService = DnDApi.createApi()

    fun getBackground(name: String) = dndApi.getBackground(name)
}