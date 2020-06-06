package com.example.dndapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dndapp.database.api.DnDApiRepository
import com.example.dndapp.database.characters.DnDCharacterRepository
import com.example.dndapp.database.stats.StatsRepository
import com.example.dndapp.model.DnDCharacter
import com.example.dndapp.model.api.dataClasses.Result
import com.example.dndapp.model.stats.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val standardCharacterID = 1 //TODO: use this for standard char number

    private val dndCharacterRepository = DnDCharacterRepository(application.applicationContext)
    private val statsRepository = StatsRepository(application.applicationContext)
    private val dndApiRepository = DnDApiRepository()

    var currentCharacterID = dndCharacterRepository.getLoadedCharacter() //dit moet gecheckt worden of t wel kan

    //var currentDnDCharacter = dndCharacterRepository.getDnDCharacter(standardCharacterID)
    //var currentStrength = statsRepository.getStrength(standardCharacterID)
    /*var currentDexterity = statsRepository.getDexterity(standardCharacterID)
    var currentConstitution = statsRepository.getConstitution(standardCharacterID)
    var currentIntelligence = statsRepository.getIntelligence(standardCharacterID)
    var currentWisdom = statsRepository.getWisdom(standardCharacterID)
    var currentCharisma = statsRepository.getCharisma(standardCharacterID)*/

    val currentDnDCharacter = MutableLiveData<DnDCharacter>()
    val currentStrength = MutableLiveData<Strength>()
    val currentDexterity = MutableLiveData<Dexterity>()
    val currentConstitution = MutableLiveData<Constitution>()
    val currentIntelligence = MutableLiveData<Intelligence>()
    val currentWisdom = MutableLiveData<Wisdom>()
    val currentCharisma = MutableLiveData<Charisma>()

    var arrayOfStats: Array<Stat?> = arrayOfNulls(6)
    //val arrayOfStats = MutableLiveData<Array<Stat?>>()

    val backgroundInfo = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    //TODO: iets waarmee alles in 1 zit???
    //TODO: moet er 1 viewmodel zijn waarin dit maar 1x gedaan hoeft te worden?

    /**
    * Get a random number trivia from the repository using Retrofit.
    * onResponse if the response is successful populate the [result] object.
    * If the call encountered an error then populate the [error] object.
    */

    fun loadAllData(loadedCharacterID: Int) {
        //currentCharacterID = dndCharacterRepository.getLoadedCharacter()


        //currentCharacterID = dndCharacterRepository.getLoadedCharacter() as MutableLiveData<Int>
        /*currentCharacterID = MutableLiveData<Int>().apply {
            value = dndCharacterRepository.getLoadedCharacter().value
            Log.d("test vanuit viewmodel", dndCharacterRepository.getLoadedCharacter().value.toString())
        }*/

        currentDnDCharacter.value = dndCharacterRepository.getDnDCharacter(loadedCharacterID).value

        currentStrength.value = statsRepository.getStrength(loadedCharacterID).value
        currentDexterity.value = statsRepository.getDexterity(loadedCharacterID).value
        currentConstitution.value = statsRepository.getConstitution(loadedCharacterID).value
        currentIntelligence.value = statsRepository.getIntelligence(loadedCharacterID).value
        currentWisdom.value = statsRepository.getWisdom(loadedCharacterID).value
        currentCharisma.value = statsRepository.getCharisma(loadedCharacterID).value

        arrayOfStats[0] = currentStrength.value
        arrayOfStats[1] = currentDexterity.value
        arrayOfStats[2] = currentConstitution.value
        arrayOfStats[3] = currentIntelligence.value
        arrayOfStats[4] = currentWisdom.value
        arrayOfStats[5] = currentCharisma.value

        /*for (a in arr) {
            println(a.getName().toString() + " " + a.getBreed())
            a.moving()
        }*/
    }

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