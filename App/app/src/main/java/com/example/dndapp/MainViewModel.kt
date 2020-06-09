package com.example.dndapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.dndapp.database.api.DnDApiRepository
import com.example.dndapp.database.bagItems.BagItemRepository
import com.example.dndapp.database.characters.DnDCharacterRepository
import com.example.dndapp.database.stats.StatsRepository
import com.example.dndapp.model.BagItem
import com.example.dndapp.model.DnDCharacter
import com.example.dndapp.model.api.dataClasses.BackgroundResult
import com.example.dndapp.model.api.dataClasses.CClassResult
import com.example.dndapp.model.api.dataClasses.RaceResult
import com.example.dndapp.model.stats.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val standardCharacterID = 1 //TODO: use this for standard char number

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val dndCharacterRepository = DnDCharacterRepository(application.applicationContext)
    private val bagItemRepository = BagItemRepository(application.applicationContext)
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

    var note = Transformations.switchMap(currentCharacterID) {currentCharacterID ->
        if(currentCharacterID == null) {
            dndCharacterRepository.getNote(standardCharacterID)
        } else {
            dndCharacterRepository.getNote(currentCharacterID)
        }
    }

    val dndCharacters: LiveData<List<DnDCharacter>> = dndCharacterRepository.getAllCharacters()

    var bagItems = Transformations.switchMap(currentCharacterID) {currentCharacterID ->
        if(currentCharacterID == null) {
            bagItemRepository.getAllBagItems(standardCharacterID)
        } else {
            bagItemRepository.getAllBagItems(currentCharacterID)
        }
    }

    val backgroundInfo = MutableLiveData<String>()
    val raceSpeed = MutableLiveData<Int>()
    val error = MutableLiveData<String>()
    var raceNames = MutableLiveData<List<String>>()
    var subraceNames = MutableLiveData<List<String>>()
    var cclassNames = MutableLiveData<List<String>>()
    var backgroundNames = MutableLiveData<List<String>>()

    var racialStatBonusesRace = MutableLiveData<List<String>>()
    var racialStatBonusValueRace = MutableLiveData<Int>()

    var racialStatBonusesSubrace = MutableLiveData<List<String>>()
    var racialStatBonusValueSubrace = MutableLiveData<Int>()

    //TODO: this ^

    //TODO: iets waarmee alles in 1 zit???
    //TODO: moet er 1 viewmodel zijn waarin dit maar 1x gedaan hoeft te worden?

    /**
    * Get a random number trivia from the repository using Retrofit.
    * onResponse if the response is successful populate the [result] object.
    * If the call encountered an error then populate the [error] object.
    */

    fun addCharacterToDatabase(newCharacter: DnDCharacter,
                               newStrength: Strength,
                               newDexterity: Dexterity,
                               newIntelligence: Intelligence,
                               newWisdom: Wisdom,
                               newCharisma: Charisma,
                               newConstitution: Constitution) {
        ioScope.launch {
            dndCharacterRepository.insertDnDCharacter(newCharacter)
            statsRepository.insertStrength(newStrength)
            statsRepository.insertDexterity(newDexterity)
            statsRepository.insertIntelligence(newIntelligence)
            statsRepository.insertWisdom(newWisdom)
            statsRepository.insertCharisma(newCharisma)
            statsRepository.insertConstitution(newConstitution)
        }
    }

    fun changeCurrentCharacter(chosenCharacterID: Int, currentCharacterID: Int) {
        ioScope.launch {
            dndCharacterRepository.changeCurrentCharacter(chosenCharacterID, currentCharacterID)
        }
    }


    //TODO: misschien nog error handling voor bijvoorbeeld het opslaan van een lege notitie?

    fun updateNotes(loadedCharacterID: Int) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                dndCharacterRepository.updateNote(loadedCharacterID, note.value!!)
            }
        }
    }

    //Retrieve a list of all race-names from the api
    fun getRaceNames(): LiveData<List<String>> {
        dndApiRepository.getRaceNames().enqueue(object : Callback<RaceResult> {
            override fun onResponse(call: Call<RaceResult>, response: Response<RaceResult>) {
                if (response.isSuccessful) {
                    val namesList = ArrayList<String>(response.body()!!.count)
                    response.body()!!.results.forEachIndexed{index, value ->
                        namesList.add(value.name)
                        Log.d("NL$index", namesList[index])
                    }
                    raceNames.apply {
                        value = namesList
                    }

                } else {
                    error.value = "An error occurred: ${response.errorBody().toString()}"
                }
            }

            override fun onFailure(call: Call<RaceResult>, t: Throwable) {
                error.value = t.message
            }
        })
        return raceNames
    }

    //Retrieve a list of all subrace names from the api depending on the selected race
    fun getSubraceNames(chosenRace: String): LiveData<List<String>> {
        dndApiRepository.getRace(chosenRace).enqueue(object : Callback<RaceResult> {
            override fun onResponse(call: Call<RaceResult>, response: Response<RaceResult>) {
                if (response.isSuccessful) {
                    val namesList = ArrayList<String>(response.body()!!.count)
                    response.body()!!.results[0].subraces.forEachIndexed{index, value ->
                        namesList.add(value.name)
                        Log.d("SUBRACE res $index", namesList[index])
                    }
                    subraceNames.apply {
                        value = namesList
                    }

                } else {
                    error.value = "An error occurred: ${response.errorBody().toString()}"
                }
            }

            override fun onFailure(call: Call<RaceResult>, t: Throwable) {
                error.value = t.message
            }
        })
        return subraceNames
    }

    //Retrieve a list of all class names from the api, called CClass because class is a thing in Koltin
    fun getCClassNames(): LiveData<List<String>> {
        dndApiRepository.getCClassNames().enqueue(object : Callback<CClassResult> {
            override fun onResponse(call: Call<CClassResult>, response: Response<CClassResult>) {
                if (response.isSuccessful) {
                    val namesList = ArrayList<String>(response.body()!!.count)
                    response.body()!!.results.forEachIndexed{index, value ->
                        namesList.add(value.name)
                        Log.d("NL$index", namesList[index])
                    }
                    cclassNames.apply {
                        value = namesList
                    }

                } else {
                    error.value = "An error occurred: ${response.errorBody().toString()}"
                }
            }

            override fun onFailure(call: Call<CClassResult>, t: Throwable) {
                error.value = t.message
            }
        })
        return cclassNames
    }

    //Retrieve a list of all background names from the api
    fun getBackgroundNames(): LiveData<List<String>> {
        dndApiRepository.getBackgroundNames().enqueue(object : Callback<BackgroundResult> {
            override fun onResponse(call: Call<BackgroundResult>, response: Response<BackgroundResult>) {
                if (response.isSuccessful) {
                    val namesList = ArrayList<String>(response.body()!!.count)
                    response.body()!!.results.forEachIndexed{index, value ->
                        namesList.add(value.name)
                        Log.d("NL$index", namesList[index])
                    }
                    backgroundNames.apply {
                        value = namesList
                    }

                } else {
                    error.value = "An error occurred: ${response.errorBody().toString()}"
                }
            }

            override fun onFailure(call: Call<BackgroundResult>, t: Throwable) {
                error.value = t.message
            }
        })
        return backgroundNames
    }

    //Retrieve the background info for a chosen background
    fun getBackgroundInfo(charBackground: String) {
        dndApiRepository.getBackground(charBackground).enqueue(object : Callback<BackgroundResult> {
            override fun onResponse(call: Call<BackgroundResult>, response: Response<BackgroundResult>) {
                if (response.isSuccessful) {
                    backgroundInfo.value = response.body()!!.results[0].desc
                    Log.d("Result background api", backgroundInfo.value.toString())
                } else {
                    error.value = "An error occurred: ${response.errorBody().toString()}"
                }
            }

            override fun onFailure(call: Call<BackgroundResult>, t: Throwable) {
                error.value = t.message
            }
        })
    }

    //Retrieve the speed for a chosen race
    fun getRaceSpeed(charRace: String) {
        dndApiRepository.getRace(charRace).enqueue(object : Callback<RaceResult> {
            override fun onResponse(call: Call<RaceResult>, response: Response<RaceResult>) {
                if (response.isSuccessful) {
                    raceSpeed.value = response.body()!!.results[0].speed.walk
                    Log.d("Result race api", raceSpeed.value.toString())
                } else {
                    error.value = "An error occurred: ${response.errorBody().toString()}"
                }
            }

            override fun onFailure(call: Call<RaceResult>, t: Throwable) {
                error.value = t.message
            }
        })
    }
    //Retrieve a list of all subrace names from the api depending on the selected race
    /*fun getRacialStatBonuses(chosenRace: String, chosenSubrace: String): LiveData<List<String>> {
        dndApiRepository.getRace(chosenRace).enqueue(object : Callback<RaceResult> {
            override fun onResponse(call: Call<RaceResult>, response: Response<RaceResult>) {
                if (response.isSuccessful) {
                    val namesList = ArrayList<String>(response.body()!!.count)
                    response.body()!!.results[0].subraces.forEachIndexed{index, value ->
                        namesList.add(value.name)
                        Log.d("SUBRACE res $index", namesList[index])
                    }
                    subraceNames.apply {
                        value = namesList
                    }

                } else {
                    error.value = "An error occurred: ${response.errorBody().toString()}"
                }
            }

            override fun onFailure(call: Call<RaceResult>, t: Throwable) {
                error.value = t.message
            }
        })

        return subraceNames
    }*/


    /*fun onAppClosure() {
        //TODO: saving the current selected character, when it changes, niet perse hier
    }*/
}