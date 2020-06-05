package com.example.dndapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dndapp.database.api.DnDApiRepository
import com.example.dndapp.database.characters.DnDCharacterRepository
import com.example.dndapp.database.stats.StatsRepository
import com.example.dndapp.model.DnDCharacter
import com.example.dndapp.model.api.dataClasses.Result
import com.example.dndapp.model.stats.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val standardCharacterID = 1

    private val dndCharacterRepository = DnDCharacterRepository(application.applicationContext)
    private val statsRepository = StatsRepository(application.applicationContext)
    private val dndApiRepository = DnDApiRepository()

    private val mainScope = CoroutineScope(Dispatchers.Main)

    private var currentCharacterID = dndCharacterRepository.getLoadedCharacter()
    val currentDnDCharacter = dndCharacterRepository.getDnDCharacter(currentCharacterID.value!!)
    val currentStrength = statsRepository.getStrength(currentCharacterID.value!!)
    val currentDexterity = statsRepository.getDexterity(currentCharacterID.value!!)
    val currentConstitution = statsRepository.getConstitution(currentCharacterID.value!!)
    val currentIntelligence = statsRepository.getIntelligence(currentCharacterID.value!!)
    val currentWisdom = statsRepository.getWisdom(currentCharacterID.value!!)
    val currentCharisma = statsRepository.getCharisma(currentCharacterID.value!!)

    val backgroundInfo = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    //TODO: iets waarmee alles in 1 zit???
    //TODO: moet er 1 viewmodel zijn waarin dit maar 1x gedaan hoeft te worden?

    /**
    * Get a random number trivia from the repository using Retrofit.
    * onResponse if the response is successful populate the [result] object.
    * If the call encountered an error then populate the [error] object.
    */

    //TODO: maak een functie die een character laadt als je m verandert

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
    }

    fun onAppClosure() {
        //TODO: saving the current selected character, when it changes, niet perse hier
    }
}