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
import com.example.dndapp.model.stats.Stat

public class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val standardCharacterID = 1 //TODO: use this for standard char number

    private val dndCharacterRepository = DnDCharacterRepository(application.applicationContext)
    private val statsRepository = StatsRepository(application.applicationContext)
    private val dndApiRepository = DnDApiRepository()

    //private val mainScope = CoroutineScope(Dispatchers.Main)




    /*var currentCharacterID = MutableLiveData<Int>().apply {
        value = dndCharacterRepository.getLoadedCharacter().value
    }*/

    var currentCharacterID = dndCharacterRepository.getLoadedCharacter() //dit moet gecheckt worden of t wel kan

    var currentDnDCharacter = dndCharacterRepository.getDnDCharacter(standardCharacterID)
    var currentStrength = statsRepository.getStrength(standardCharacterID)
    var currentDexterity = statsRepository.getDexterity(standardCharacterID)
    var currentConstitution = statsRepository.getConstitution(standardCharacterID)
    var currentIntelligence = statsRepository.getIntelligence(standardCharacterID)
    var currentWisdom = statsRepository.getWisdom(standardCharacterID)
    var currentCharisma = statsRepository.getCharisma(standardCharacterID)

    var statArray = arrayOf<Stat>(
        currentStrength,
        currentDexterity
    )

    val backgroundInfo = MutableLiveData<String>()
    val error = MutableLiveData<String>()


    //TODO: iets waarmee alles in 1 zit???
    //TODO: moet er 1 viewmodel zijn waarin dit maar 1x gedaan hoeft te worden?

    /**
    * Get a random number trivia from the repository using Retrofit.
    * onResponse if the response is successful populate the [result] object.
    * If the call encountered an error then populate the [error] object.
    */

    fun toStat() {

    }

    fun loadAllData(loadedCharacterID: Int) {
        //currentCharacterID = dndCharacterRepository.getLoadedCharacter()


        //currentCharacterID = dndCharacterRepository.getLoadedCharacter() as MutableLiveData<Int>
        /*currentCharacterID = MutableLiveData<Int>().apply {
            value = dndCharacterRepository.getLoadedCharacter().value
            Log.d("test vanuit viewmodel", dndCharacterRepository.getLoadedCharacter().value.toString())
        }*/

        currentDnDCharacter = dndCharacterRepository.getDnDCharacter(loadedCharacterID)
        currentStrength = statsRepository.getStrength(loadedCharacterID)
        currentDexterity = statsRepository.getDexterity(loadedCharacterID)
        currentConstitution = statsRepository.getConstitution(loadedCharacterID)
        currentIntelligence = statsRepository.getIntelligence(loadedCharacterID)
        currentWisdom = statsRepository.getWisdom(loadedCharacterID)
        currentCharisma = statsRepository.getCharisma(loadedCharacterID)


    }

    //TODO: maak een functie die een character laadt als je m verandert

    fun getBackgroundInfo(charBackground: String) {
        dndApiRepository.getBackground(charBackground).enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful) {
                    backgroundInfo.value = response.body()!!.results[0].desc
                    Log.d("RESULTAAT:", backgroundInfo.value.toString())
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