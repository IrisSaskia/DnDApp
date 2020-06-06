package com.example.dndapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.dndapp.database.api.DnDApiRepository
import com.example.dndapp.database.characters.DnDCharacterRepository
import com.example.dndapp.database.stats.StatsRepository
import com.example.dndapp.model.api.dataClasses.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val standardCharacterID = 1 //TODO: use this for standard char number

    private val dndCharacterRepository = DnDCharacterRepository(application.applicationContext)
    private val statsRepository = StatsRepository(application.applicationContext)
    private val dndApiRepository = DnDApiRepository()

    var currentCharacterID = dndCharacterRepository.getLoadedCharacter() //dit moet gecheckt worden of t wel kan

    var currentDnDCharacter = Transformations.switchMap(currentCharacterID) { currentCharacterID ->
        if(currentCharacterID == null) {
            dndCharacterRepository.getDnDCharacter(standardCharacterID)
        } else {
            dndCharacterRepository.getDnDCharacter(currentCharacterID)
        }
    }
    var currentStrength = Transformations.switchMap(currentCharacterID) { currentCharacterID ->
        if(currentCharacterID == null) {
            statsRepository.getStrength(standardCharacterID)
        } else {
            statsRepository.getStrength(currentCharacterID)
        }
    }
    var currentDexterity = Transformations.switchMap(currentCharacterID) { currentCharacterID ->
        if(currentCharacterID == null) {
            statsRepository.getDexterity(standardCharacterID)
        } else {
            statsRepository.getDexterity(currentCharacterID)
        }
    }
    var currentConstitution = Transformations.switchMap(currentCharacterID) { currentCharacterID ->
        if(currentCharacterID == null) {
            statsRepository.getConstitution(standardCharacterID)
        } else {
            statsRepository.getConstitution(currentCharacterID)
        }
    }
    var currentIntelligence = Transformations.switchMap(currentCharacterID) { currentCharacterID ->
        if(currentCharacterID == null) {
            statsRepository.getIntelligence(standardCharacterID)
        } else {
            statsRepository.getIntelligence(currentCharacterID)
        }
    }
    var currentWisdom = Transformations.switchMap(currentCharacterID) { currentCharacterID ->
        if(currentCharacterID == null) {
            statsRepository.getWisdom(standardCharacterID)
        } else {
            statsRepository.getWisdom(currentCharacterID)
        }
    }
    var currentCharisma = Transformations.switchMap(currentCharacterID) { currentCharacterID ->
        if(currentCharacterID == null) {
            statsRepository.getCharisma(standardCharacterID)
        } else {
            statsRepository.getCharisma(currentCharacterID)
        }
    }

    //var currentDnDCharacter = dndCharacterRepository.getDnDCharacter(currentCharacterID.value?: standardCharacterID) //TODO: miss op deze manier oplossen???
    /*var currentStrength = statsRepository.getStrength(currentCharacterID.value?: standardCharacterID)
    var currentDexterity = statsRepository.getDexterity(currentCharacterID.value?: standardCharacterID)
    var currentConstitution = statsRepository.getConstitution(currentCharacterID.value?: standardCharacterID)
    var currentIntelligence = statsRepository.getIntelligence(currentCharacterID.value?: standardCharacterID)
    var currentWisdom = statsRepository.getWisdom(currentCharacterID.value?: standardCharacterID)
    var currentCharisma = statsRepository.getCharisma(currentCharacterID.value?: standardCharacterID)*/

    val backgroundInfo = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    //TODO: iets waarmee alles in 1 zit???
    //TODO: moet er 1 viewmodel zijn waarin dit maar 1x gedaan hoeft te worden?

    /**
    * Get a random number trivia from the repository using Retrofit.
    * onResponse if the response is successful populate the [result] object.
    * If the call encountered an error then populate the [error] object.
    */

    /*fun loadAllData(loadedCharacterID: Int) {


        currentDnDCharacter = dndCharacterRepository.getDnDCharacter(loadedCharacterID)

        Log.d("laden", "bezig")
        //Log.d("characterinfotest", currentDnDCharacter.value?.name.toString())

        currentStrength = statsRepository.getStrength(loadedCharacterID)
        currentDexterity = statsRepository.getDexterity(loadedCharacterID)
        currentConstitution = statsRepository.getConstitution(loadedCharacterID)
        currentIntelligence = statsRepository.getIntelligence(loadedCharacterID)
        currentWisdom = statsRepository.getWisdom(loadedCharacterID)
        currentCharisma = statsRepository.getCharisma(loadedCharacterID)
    }
*/
    //TODO: maak een functie die een character laadt als je m verandert

    fun getBackgroundInfo(charBackground: String) {
        dndApiRepository.getBackground(charBackground).enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful) {
                    backgroundInfo.value = response.body()!!.results[0].desc
                    Log.d("Result background api", backgroundInfo.value.toString())
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