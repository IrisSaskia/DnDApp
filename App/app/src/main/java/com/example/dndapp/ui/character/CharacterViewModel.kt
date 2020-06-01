package com.example.dndapp.ui.character

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dndapp.database.api.DnDApiRepository
import com.example.dndapp.database.characters.DnDCharacterRepository
import com.example.dndapp.model.DnDCharacter
import com.example.dndapp.model.api.dataClasses.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterViewModel(application: Application) : AndroidViewModel(application) {
    /*private val dndCharacterRepository = DnDCharacterRepository(application.applicationContext)

    var currentDnDCharacter: LiveData<DnDCharacter?> = dndCharacterRepository.getDnDCharacter(1)

    private val dndApiRepository = DnDApiRepository()
    val backgroundInfo = MutableLiveData<String>()

    val error = MutableLiveData<String>()

    *//**
     * Get a random number trivia from the repository using Retrofit.
     * onResponse if the response is successful populate the [result] object.
     * If the call encountered an error then populate the [error] object.
     *//*
    fun getBackgroundInfo(charBackground: String) {
        dndApiRepository.getBackground(charBackground).enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful) {
                    backgroundInfo.value = response.body()!!.results[0].desc
                    print("RESULTAAT:" + backgroundInfo.value)
                } else {
                    error.value = "An error occurred: ${response.errorBody().toString()}"
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                error.value = t.message
            }
        })
    }*/
}