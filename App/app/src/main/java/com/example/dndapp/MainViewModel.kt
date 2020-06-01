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
    private val dndCharacterRepository = DnDCharacterRepository(application.applicationContext)
    private val statsRepository = StatsRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    var currentDnDCharacter: LiveData<DnDCharacter?> = dndCharacterRepository.getDnDCharacter(1)
    var currentStrength: LiveData<Strength?> = statsRepository.getStrength(1)
    var currentDexterity: LiveData<Dexterity?> = statsRepository.getDexterity(1)
    var currentConstitution: LiveData<Constitution?> = statsRepository.getConstitution(1)
    var currentIntelligence: LiveData<Intelligence?> = statsRepository.getIntelligence(1)
    var currentWisdom: LiveData<Wisdom?> = statsRepository.getWisdom(1)
    var currentCharisma: LiveData<Charisma?> = statsRepository.getCharisma(1)

    private val dndApiRepository = DnDApiRepository()
    val backgroundInfo = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    //TODO: iets waarmee alles in 1 zit???
    //TODO: moet er 1 viewmodel zijn waarin dit maar 1x gedaan hoeft te worden?

    /*fun updateCharacter() {
        mainScope.launch {

        }
    }*/

    /**
    * Get a random number trivia from the repository using Retrofit.
    * onResponse if the response is successful populate the [result] object.
    * If the call encountered an error then populate the [error] object.
    */
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
}