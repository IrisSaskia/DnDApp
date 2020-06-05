package com.example.dndapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dndapp.database.api.DnDApiRepository
import com.example.dndapp.database.characters.DnDCharacterRepository
import com.example.dndapp.database.stats.StatsRepository
import com.example.dndapp.model.api.dataClasses.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log

public class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val standardCharacterID = 1 //TODO: use this for standard char number

    private val dndCharacterRepository = DnDCharacterRepository(application.applicationContext)
    private val statsRepository = StatsRepository(application.applicationContext)
    private val dndApiRepository = DnDApiRepository()

    //private val mainScope = CoroutineScope(Dispatchers.Main)

    /*
    val currentDnDCharacter = dndCharacterRepository.getDnDCharacter(currentCharacterID)
    val currentStrength = statsRepository.getStrength(currentCharacterID)
    val currentDexterity = statsRepository.getDexterity(currentCharacterID)
    val currentConstitution = statsRepository.getConstitution(currentCharacterID)
    val currentIntelligence = statsRepository.getIntelligence(currentCharacterID)
    val currentWisdom = statsRepository.getWisdom(currentCharacterID)
    val currentCharisma = statsRepository.getCharisma(currentCharacterID)

    val backgroundInfo = MutableLiveData<String>()
    val error = MutableLiveData<String>()*/

    /*var currentCharacterID = MutableLiveData<Int>().apply {
        value = dndCharacterRepository.getLoadedCharacter().value
    }*/

    var currentCharacterID = dndCharacterRepository.getLoadedCharacter()



    //TODO: iets waarmee alles in 1 zit???
    //TODO: moet er 1 viewmodel zijn waarin dit maar 1x gedaan hoeft te worden?

    /**
    * Get a random number trivia from the repository using Retrofit.
    * onResponse if the response is successful populate the [result] object.
    * If the call encountered an error then populate the [error] object.
    */

    fun loadAllData() {
        currentCharacterID = dndCharacterRepository.getLoadedCharacter()
        //currentCharacterID = dndCharacterRepository.getLoadedCharacter() as MutableLiveData<Int>
        /*currentCharacterID = MutableLiveData<Int>().apply {
            value = dndCharacterRepository.getLoadedCharacter().value
            Log.d("test vanuit viewmodel", dndCharacterRepository.getLoadedCharacter().value.toString())
        }*/

        /*val currentDnDCharacter = dndCharacterRepository.getDnDCharacter(currentCharacterID)
        val currentStrength = statsRepository.getStrength(currentCharacterID)
        val currentDexterity = statsRepository.getDexterity(currentCharacterID)
        val currentConstitution = statsRepository.getConstitution(currentCharacterID)
        val currentIntelligence = statsRepository.getIntelligence(currentCharacterID)
        val currentWisdom = statsRepository.getWisdom(currentCharacterID)
        val currentCharisma = statsRepository.getCharisma(currentCharacterID)

        val backgroundInfo = MutableLiveData<String>()
        val error = MutableLiveData<String>()*/
    }

    //TODO: maak een functie die een character laadt als je m verandert

    /*fun getBackgroundInfo(charBackground: String) {
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

    fun onAppClosure() {
        //TODO: saving the current selected character, when it changes, niet perse hier
    }
}